package org.dp.demowsreactiveclt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
@SpringBootApplication
public class DemoWsReactiveCltApplication {
    Logger logger = LoggerFactory.getLogger(DemoWsReactiveCltApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DemoWsReactiveCltApplication.class, args);
    }

    @Bean
    public void wsClientHandler() {
        URI url = URI.create("ws://localhost:8080/out");
        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();

        client.execute(url, session -> session.receive()
                .doOnNext(webSocketMessage -> {
                    System.out.println(webSocketMessage.getPayloadAsText());
                })
                .then()
        );
    }

}
