# 🔹 Streaming Response in Spring AI

Spring AI supports both blocking responses and streaming responses when interacting with AI models.


---

## 🧩 Blocking Response

- The client sends the prompt and waits until the full response is generated.
- Suitable for short queries or when the response size is predictable.
### Example:

```text
ChatResponse response = chatClient.prompt("Tell me a joke").call().chatResponse();
System.out.println(response.getResult().getOutput().getContent());

```

---

## ⚡ Streaming Response

- The client receives partial outputs as soon as they are available.
- Allows processing chunks of the response in real time.
- Ideal for long-running tasks, large outputs, or live UI updates.
### Example:

```text
chatClient.prompt("Write a story").stream()
    .chatResponse()
    .doOnNext(chunk -> System.out.print(chunk.getResult().getOutput().getContent()))
    .blockLast();

```

---

## ✅ Why Prefer Streaming Over Blocking?

### 1. Reduced Latency
    - Output starts arriving immediately without waiting for the full response.
### 2. Better User Experience
    - UI can display text progressively (like ChatGPT typing effect).
### 3. Efficient for Large Responses
    - Process chunks on the fly instead of holding the entire output in memory.
### 4. Real-Time Interactivity
    - Useful for chatbots, live assistants, and continuous feedback systems.

---

## 📊 Summary

| Aspect          | Blocking Response               | Streaming Response            |
|-----------------|---------------------------------|-------------------------------|
| **Latency**     | High (wait for full response)   | Low (starts immediately)      |
| **Memory Usage**| Higher                          | Lower (processed in chunks)   |
| **Use Case**    | Short queries, simple tasks     | Long tasks, real-time apps    |
| **User Experience** | Static                      | Interactive, responsive       |







