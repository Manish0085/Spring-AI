# 🔹 Custom Advisors in Spring AI

Spring AI provides the Advisor interface (Advisor, CallAdvisor and StreamAdvisor) to extend and customize the behavior of ChatClient or EmbeddingClient.
Custom advisors allow you to intercept prompts, responses, and even track metadata such as token usage.

---

## ✨ Creating a Custom Advisor

### 1. Implement the Advisor Interface

```java

public class CustomAdvisor implements CallAdvisor, StreamAdvisor {


    private Logger logger = LoggerFactory.getLogger(TokenPrintAdvisor.class);


    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        this.logger.info("My Token Print Advisor is called: ");
        this.logger.info("Request: "+ chatClientRequest.prompt()
                .getContents());

        ChatClientResponse chatClientResponse = callAdvisorChain
                .nextCall(chatClientRequest);

        this.logger.info("Token Advisor: Response received from the model");
        this.logger.info("Response: "+chatClientResponse
                .chatResponse()
                .getResult()
                .getOutput()
                .getText());

        this.logger.info("Prompt Token: "+chatClientResponse
                .chatResponse()
                .getMetadata()
                .getUsage()
                .getPromptTokens());

        this.logger.info("Completion Token: "+chatClientResponse
                .chatResponse()
                .getMetadata()
                .getUsage()
                .getCompletionTokens());

        this.logger.info("Total token consumed: "+chatClientResponse
                .chatResponse()
                .getMetadata()
                .getUsage()
                .getTotalTokens());

        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return null;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

```


### 2. Attach to the ChatClient

```java
@Bean
public ChatClient chatClientConfig(ChatClient.Builder builder){
    return builder
//                .defaultAdvisors(new TokenPrintAdvisor(), new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("game", "Password", "APIKEY")))
            .defaultAdvisors(new TokenPrintAdvisor(), new SafeGuardAdvisor(List.of("game", "Password", "APIKEY")))
            .defaultSystem("You are a helpful coding assistant. You are an expert in coding.")
            .defaultOptions(OllamaOptions.builder()
                    .model("codellama")
                    .temperature(0.5)
                    .build())
            .build();
}

```

---


## 📊 Token Usage Tracking

When responses are returned from the AI model, Spring AI provides a TokenUsage object that contains:

- Prompt Tokens → Tokens consumed by the input prompt.
- Completion Tokens → Tokens consumed by the AI-generated response.
- Total Tokens → Sum of both prompt and completion tokens.


### Example:

```text
Prompt tokens: 45
Completion tokens: 60
Total tokens: 105

```

---

## ✅ Benefits of Using Advisors for Token Tracking

- Centralized token usage monitoring for cost control.
- Easy to log or store token statistics per request.
- Extensible for analytics, billing, or debugging.






