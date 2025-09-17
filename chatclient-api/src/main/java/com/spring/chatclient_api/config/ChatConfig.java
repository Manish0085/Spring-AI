package com.spring.chatclient_api.config;

import com.spring.chatclient_api.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {


    @Bean
    public ChatClient chatClientConfig(ChatClient.Builder builder){
        return builder
                .defaultOptions(OllamaOptions.builder()
                        .model("codellama")
                        .temperature(0.5)
                        .build())
                .build();
    }
}
