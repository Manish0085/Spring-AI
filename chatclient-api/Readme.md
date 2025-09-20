# Prompt Defaults

## Globally by setting in the application.properties

```properties
spring.ai.ollama.base.url=http://localhost:11434
spring.ai.ollama.chat.options.model=codellama
spring.ai.ollama.chat.options.temperature=0.7
```

---

## While creating Prompts

```
Prompt prompt = new Prompt(query, OpenAiChatOptions.builder()
                                .model("codellama)
                                .temperature(0.7)
                                .maxTokens(100)
                                .build());
```

---

## While creating Builder of ChatClient

```
private ChatClient chatclient;
public ChatController(ChatClient.Builder builder){
    this.chatClient = builder
                    .defaultOptions(OpenAiChatOptions.builder()
                        .model("codellama)
                        .temerature(0.7)
                        .maxToken(100)
                        .build())
                    .build();
}
```

---


## Prompt Templates

```
public String chat(String query){
    Prompt prompt = new Prompt(query);
    String quesryStr = "Act as an expert in coding and programming. Always write program in Java.: {query}";
    var result = chatClient
                .prompt()
                .user(u -> u.text(queryStr).param("query", queryStr))
                .call()
                .content();
    return result; 
}

```

