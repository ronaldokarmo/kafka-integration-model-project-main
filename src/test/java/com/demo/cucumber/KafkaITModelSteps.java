package com.demo.cucumber;

import com.demo.support.SpringITSupport;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.RecordedRequest;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@Slf4j
public class KafkaITModelSteps extends SpringITSupport {

    //Results
    private RecordedRequest gatewayServerRequest;
    private String retornoObtido;

    //Given
    @Dado("que uma mensagem {string} é enviada no tópico {string}")
    public void o_evento_chegar_no_topico(String mensagem, String topico) {
        this.kafkaProducer.send(topico, mensagem);
    }

    //When
    @Quando("o micro-serviço receber o evento, processar e devolver uma resposta")
    @SneakyThrows
    public void o_micro_servico_receber_o_evento() {
        this.kafkaConsumer.getLatch().await(20000, TimeUnit.MILLISECONDS);
        this.kafkaConsumer.refreshLatch();
        this.retornoObtido = this.kafkaConsumer.getPayload();
        log.debug("Retorno Obtido: " + this.retornoObtido);
    }

    //Then
    @Então("a mensagem retornada deve conter {string}")
    public void mensagem_retornada_deve_conter(String mensagemResultado) {
        assertTrue("Não foi encontrado o valor esperado", this.retornoObtido.contains(mensagemResultado));
    }
}
