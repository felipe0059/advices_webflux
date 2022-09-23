package br.com.webfluxstudies.client;

import br.com.webfluxstudies.response.AdviceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class AdviceClient {

    private final WebClient webClient;

    public AdviceClient(WebClient.Builder builder){
        webClient = builder.baseUrl("https://api.adviceslip.com").build();
    }

    public Mono<String> randomAdvice(){
        log.info("Searching some advice");
        return webClient
                .get()
                .uri("/advice")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Please, check the url")))
                .bodyToMono(String.class);
    }

    public Mono<String> findAdviceById(Integer slip_id){
        log.info("Searching the advice by code [{}]", slip_id);
        return webClient
                .get()
                .uri("/advice/"+slip_id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Please, check the advice code")))
                                .bodyToMono(String.class);
    }
}
