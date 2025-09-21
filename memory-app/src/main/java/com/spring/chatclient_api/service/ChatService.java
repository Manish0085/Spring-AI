package com.spring.chatclient_api.service;

import com.spring.chatclient_api.entity.Tut;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService {



    String chat(String query);

    String chatWithInMemory(String query, String userId);
}
