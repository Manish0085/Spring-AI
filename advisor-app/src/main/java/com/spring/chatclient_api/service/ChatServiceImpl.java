package com.spring.chatclient_api.service;

import com.spring.chatclient_api.entity.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService{


    private ChatClient chatClient;


    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

    public ChatServiceImpl(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    public String chatTemplate(String query){
        return chatClient
                .prompt()
//                .advisors(new SimpleLoggerAdvisor())
                .system(system -> {
                    system.text(this.systemMessage).params(Map.of(
                            "name", "Stiphen Marek",
                            "role", "Cloud Engineer"
                            ));
                })
                .user(user -> {
                    user.text(this.userMessage).param("question", query);
                })
                .call()
                .content();
    }
}
