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
Prompt prompt = new Prompt(query, OllamaChatOptions.builder()
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
                    .defaultOptions(OllamaChatOptions.builder()
                        .model("codellama)
                        .temerature(0.7)
                        .maxToken(100)
                        .build())
                    .build();
}
```

