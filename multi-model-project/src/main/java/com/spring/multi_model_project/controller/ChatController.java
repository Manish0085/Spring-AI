package com.spring.multi_model_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    private ChatClient openAiChatClient;
    private ChatClient ollamaAiChatClient;


    public ChatController(@Qualifier("openAiChatClient") ChatClient openAiChatClient, @Qualifier("ollamaChatClient") ChatClient ollamaChatClient){
        this.openAiChatClient = openAiChatClient;
        this.ollamaAiChatClient = ollamaChatClient;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(
            @RequestParam(value = "q", required = true) String q
    ){
        String content = ollamaAiChatClient
                .prompt(q)
                .call()
                .content();
        return ResponseEntity.ok(content);
    }
}
