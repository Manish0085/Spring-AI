package com.spring.chatclient_api.config;

import com.spring.chatclient_api.advisor.CustomAdvisor;
import com.spring.chatclient_api.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Max;
import java.util.List;

@Configuration
public class ChatConfig {


    @Bean
    public ChatClient chatClientConfig(ChatClient.Builder builder){
        return builder
                .defaultAdvisors(new CustomAdvisor(), new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("game", "Password", "APIKEY")))
                .defaultSystem("You are a helpful coding assistant. You are an expert in coding.")
                .defaultOptions(OllamaOptions.builder()
                        .model("codellama")
                        .temperature(0.5)
                        .build())
                .build();
    }
}
