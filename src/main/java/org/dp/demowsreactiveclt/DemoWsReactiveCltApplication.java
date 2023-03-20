package org.dp.demowsreactiveclt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;

import java.net.URI;
@SpringBootApplication
public class DemoWsReactiveCltApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoWsReactiveCltApplication.class, args);
    }

    @Bean
    public void wsClientHandler() {
        URI url = URI.create("ws://localhost:8080/out");
        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();
        WsReceiverHandler wsReceiverHandler = new WsReceiverHandler();

        client.execute(url, wsReceiverHandler).subscribe();
    }

}
