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

---

## 🔹 Flow

```text
Prompt Templating ---> [PromptTemplate] ---> {render} ---> [Prompt] ---> {create} ---> [Message] ---> ChatClient
                              |
                      Template Renderer


``` 
---

## Prompt Design Best Practices for Spring AI

This document outlines the best practices for designing **system and user prompts** in a Spring AI application, especially when using Retrieval-Augmented Generation (RAG).

----

### 1. Always Pin a Short, Strict System Prompt

- System prompts must be concise and fixed in every API call.
- They guide the AI’s behavior and set boundaries.
---
### 2. Separate Roles Correctly

- Keep `system` and `user` messages distinct.
- Never merge user text into the system prompt.
---
### 3. Enforce Hard Constraints About RAG

- Instruct the AI on how to treat retrieved documents.
- Only allow relevant context to influence the answer.
---
### 4. Enforce Length Limits to Save Tokens and Costs

- Bound the input sizes before sending them to the model.
- Truncate or summarize user inputs or RAG content.

**Example:**
- Max user input length: 500 characters
- Max RAG chunk length: 200 words (pre-truncated)

---

### 5. Filter Sensitive Words in User Input

- Validate and sanitize user inputs before sending to the AI.
- Block sensitive words like `password`, `API key`, `credit card`.


