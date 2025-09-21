package com.spring.chatclient_api.conttroller;

import com.spring.chatclient_api.entity.Tut;
import com.spring.chatclient_api.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping
public class ChatController {

    private ChatService service;


    public ChatController (ChatService service){
        this.service = service;
    }


    @GetMapping("/chat")
    public ResponseEntity<String> chat(
            @RequestParam(value = "q", required = true) String q
    ){
        return ResponseEntity.ok(service.chat(q));
    }
}
