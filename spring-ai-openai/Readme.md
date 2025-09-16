# Spring Boot + OpenAI (Spring AI)

This project demonstrates how to integrate **OpenAI models** (like `gpt-3.5-turbo`, `gpt-4`, etc.) into a **Spring Boot application** using **Spring AI**.

---

## 🔧 Setup & Installation

### 1. Get OpenAI API Key
- Create an account at [https://platform.openai.com](https://platform.openai.com)
- Generate your API key from the [API Keys Dashboard](https://platform.openai.com/account/api-keys)

### 2. Add the following dependency into `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
    <version>0.8.0</version>
</dependency>
```

### ⚙️ Required Properties

```properties
spring:
  ai:
    openai:
      api-key: YOUR_OPENAI_API_KEY
      chat:
        options:
          model: gpt-3.5-turbo
          temperature: 0.7
```