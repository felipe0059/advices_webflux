package br.com.webfluxstudies.controller;

import br.com.webfluxstudies.client.AdviceClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AdviceController {
    @Autowired
    AdviceClient adviceClient;

    @GetMapping("/advice")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> getAdvice() {
        return adviceClient.randomAdvice();
    }

    @GetMapping("/advice/{slip_id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> findAdviceById (@PathVariable Integer slip_id){
        return adviceClient.findAdviceById(slip_id);
    }
}
