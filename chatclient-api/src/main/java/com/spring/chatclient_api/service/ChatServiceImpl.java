package com.spring.chatclient_api.service;

import com.spring.chatclient_api.entity.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{


    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }



    @Override
    public String chat(String query) {
//        String prompt = "tell me about virat kohli.";

        // Generating Prompts
//        String content = chatClient
//                .prompt()
//                .user(prompt)
//                .system("As a expert in cricket")
//                .call()
//                .content();
//

        Prompt prompt1 = new Prompt(query);
        String content = chatClient
                .prompt(prompt1)
                .call()
                .content();

        ChatResponseMetadata metadata = chatClient
                .prompt(prompt1)
                .call()
                .chatResponse()
                .getMetadata();

        Generation result = chatClient
                .prompt(prompt1)
                .call()
                .chatResponse()
                .getResult();

        String text = result
                .getOutput()
                .getText();

        return text;
    }

    @Override
    public Tut chatToEntity(String query) {
        Tut entity = chatClient
                .prompt(query)
                .call()
                .entity(Tut.class);
        return entity;

    }

    @Override
    public List<Tut> chatToListEntity(String query) {

        List<Tut> entity = chatClient
                .prompt(query)
                .call()
                .entity(new ParameterizedTypeReference<List<Tut>>() {
                });

        return entity;
    }
}
