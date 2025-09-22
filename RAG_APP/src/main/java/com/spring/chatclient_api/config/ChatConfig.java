package com.spring.chatclient_api.config;

import com.spring.chatclient_api.advisor.CustomAdvisor;
import com.spring.chatclient_api.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Max;
import java.util.List;

@Configuration
public class ChatConfig {


    // changing configuration of JdbcChatMemoryRepository
    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
        return MessageWindowChatMemory
                .builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(10)
                .build();
    }

    private Logger logger = LoggerFactory.getLogger(ChatConfig.class);
    @Bean
    public ChatClient chatClientConfig(ChatClient.Builder builder, ChatMemory chatMemory){

        logger.info("ChatMemoryImplementation class: "+chatMemory.getClass().getName());

        MessageChatMemoryAdvisor messageChatMemoryAdvisore = MessageChatMemoryAdvisor.builder(chatMemory).build();


        return builder
                .defaultAdvisors(messageChatMemoryAdvisore, new CustomAdvisor(), new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("game", "Password", "APIKEY")))
                .defaultSystem("You are a helpful coding assistant. You are an expert in coding.")
                .defaultOptions(OllamaOptions.builder()
                        .model("codellama")
                        .temperature(0.5)
                        .build())
                .build();
    }
}
