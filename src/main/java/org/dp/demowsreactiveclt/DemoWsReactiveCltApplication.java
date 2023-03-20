package org.dp.demowsreactiveclt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import java.net.URI;

@SpringBootApplication
public class DemoWsReactiveCltApplication {
	private final WebSocketClient client;

	public DemoWsReactiveCltApplication(WebSocketClient client) {
		this.client = client;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoWsReactiveCltApplication.class, args);
	}

	@Bean
	public void wsClientHandler(){
		URI url = URI.create("ws://localhost:8080/out");
		client.execute(url, session ->
				session
					.receive()
					.doOnNext(System.out::println)
					.then());
	}

}
