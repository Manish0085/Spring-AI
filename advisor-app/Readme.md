# 🌱 Spring AI Advisors

Advisors in **Spring AI** are a powerful mechanism that let you **intercept, modify, and enrich prompts or responses** when working with AI models.  
Think of them as middleware or filters for AI calls — they allow you to inject additional behavior into the request/response lifecycle.

---

## 🔹 What Are Advisors?

* Advisors are applied **before or after** a model interaction.
* They allow you to:
    * Add system prompts globally
    * Enforce policies (e.g., safety filters, guardrails)
    * Modify the user input or the model’s output
    * Implement logging, caching, or monitoring
    * Chain multiple advisors together for modularity

---

## 🔹 Types of Advisors

1. **Input Advisors**
    * Run **before the request** reaches the model.
    * Example: prepend a system message, sanitize input.

2. **Output Advisors**
    * Run **after the response** is returned by the model.
    * Example: post-process text, trim content, apply custom formatting.

---

## 🔹 How Advisors Fit In

```text
User Prompt ---> [Input Advisors] ---> Model ---> [Output Advisors] ---> Final Response
```

---

## Overview

Spring AI provides advisors to simplify AI integration in your Spring Boot applications.

- **StreamAdvisor**: Handles streaming AI responses efficiently, ideal for real-time applications.
- **CallAdvisor**: Manages AI calls, including synchronous and asynchronous requests.

---

## StreamAdvisor

**Purpose:**  
StreamAdvisor allows you to process streaming AI data in real-time, enabling applications such as chat interfaces, live summarizers, and more.

**Key Features:**
- Handles continuous AI response streams
- Supports backpressure and reactive processing
- Easy integration with Spring Boot services

**Usage Example:**

```java
@Autowired
private StreamAdvisor streamAdvisor;

public void streamAIResponse(String prompt) {
    streamAdvisor.stream(prompt)
            .subscribe(response -> System.out.println(response));
}
```

---

## 🔹 How to Configure Advisors

Advisors can be registered as Spring Beans and then attached to a `ChatClient` or `EmbeddingClient`.

### Example: Adding a System Prompt Advisor

```java
@Bean
public Advisor systemPromptAdvisor() {
    return new SystemPromptAdvisor("You are a helpful assistant.");
}

@Bean
public ChatClient chatClient(ChatModel model, Advisor systemPromptAdvisor) {
    return ChatClient.builder(model)
            .defaultAdvisors(systemPromptAdvisor)
            .build();
}
```
Here, every prompt will automatically include the system instruction:
> *"You are a helpful assistant."*

---

### Example: Chaining Multiple Advisors

```java
@Bean
public ChatClient chatClient(ChatModel model, List<Advisor> advisors) {
    return ChatClient.builder(model)
            .defaultAdvisors(advisors) // Adds all registered advisors
            .build();
}

```

Spring automatically injects all Advisor beans into the list.
---

### Example: Custom Advisor

```java
public class LoggingAdvisor implements Advisor {

    @Override
    public ChatRequest adviseRequest(ChatRequest request) {
        System.out.println("User Prompt: " + request.getMessages());
        return request;
    }

    @Override
    public ChatResponse adviseResponse(ChatResponse response) {
        System.out.println("Model Response: " + response.getResult());
        return response;
    }
}

@Bean
public Advisor loggingAdvisor() {
    return new LoggingAdvisor();
}

```

This custom advisor logs both the prompt and the response.
---


## 🔹 Use Cases
- Safety / Guardrails: Filter sensitive content
- System Prompt Injection: Maintain a consistent assistant personality
- Post-processing: Transform AI output (e.g., JSON cleanup)
- Observability: Logging and monitoring AI interactions
- Caching: Save repeated prompts & responses

---

##🔹 Best Practices
- Keep advisors modular — one responsibility per advisor
- Chain advisors for complex behavior
- Use advisors for cross-cutting concerns, not business logic
- Place reusable advisors in a shared package for consistency

---



###✅ Summary

Advisors in Spring AI give you fine-grained control over how prompts and responses flow through the system. By leveraging them, you can build safer, more reliable, and more customizable AI applications with Spring Boot + Spring AI.



