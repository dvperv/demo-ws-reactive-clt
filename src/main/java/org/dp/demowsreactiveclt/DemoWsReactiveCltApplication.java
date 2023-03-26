package org.dp.demowsreactiveclt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;

import java.net.URI;
import java.util.Base64;

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

        //Auth
        HttpHeaders httpHeaders = new HttpHeaders(); //TODO клиент не коннектится
        String auth = "user1" + ":" + "password";
        httpHeaders.add("Authorization", "Basic " + new String(Base64.getEncoder().encode(auth.getBytes())));

        client.execute(url, httpHeaders, wsReceiverHandler).subscribe();
    }

}
