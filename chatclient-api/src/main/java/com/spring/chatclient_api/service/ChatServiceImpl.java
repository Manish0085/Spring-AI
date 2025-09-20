package com.spring.chatclient_api.service;

import com.spring.chatclient_api.entity.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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


    // Creating template and rendering  it
    public  String chatTemplate(){
        PromptTemplate template = PromptTemplate.builder().template("What is {techName}. Tell me example of {techExample}").build();

        // Rendering the template
        String renderedTemplate = template.render(Map.of(
                "techName", "Spring",
                "techExample", "spring Boot"
        ));


        Prompt prompt = new Prompt(renderedTemplate);
        return this.chatClient.prompt(prompt).call().content();
    }

    public String creatingPromptForSpecificRole(){
        SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate
                .builder()
                .template("You are a helpful coding assistant.You are an expert in coding.")
                .build();

        var systemMessage = systemPromptTemplate.createMessage();

        PromptTemplate promptTemplate = PromptTemplate
                .builder()
                .template("What is {techName}. Tell me example of {techExample}")
                .build();
        var userMessage = promptTemplate.createMessage(
                Map.of(
                        "techName", "Spring",
                        "techExample", "Java Exception"
                )
        );

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt).call().content();

    }

    public String creatingPromptForSpecificRoleUsingFluentApi(){

    }
}
