# Spring Boot + Ollama (Spring AI)

This project demonstrates how to integrate **Ollama AI models** (like `llama2`, `mistral`, etc.) into a **Spring Boot application** using **Spring AI**.

---

## 🔧 Setup & Installation

### 1. Install Ollama
- Download and install Ollama from [https://ollama.com](https://ollama.com)

Or run it using Docker:
```bash
docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
```

### Add the following dependency into `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-ollama-spring-boot-starter</artifactId>
    <version>0.8.0</version>
</dependency>
```

### ⚙️ Required Properties

Add the following properties into `application.properties`:

```properties
# Ollama base URL (default if running locally or via Docker)
spring.ai.ollama.base-url=http://localhost:11434

# Select the model you want to use (example: mistral, llama2, codellama etc.)
spring.ai.ollama.chat.options.model=mistral

# (Optional) Adjust temperature for creativity vs. accuracy
spring.ai.ollama.chat.options.temperature=0.7
```

> ✅ **Note:** With just this minimal configuration, the AI model is fully integrated into your Spring Boot application using Spring AI.  
> You can start calling AI models just like any other Spring service.


