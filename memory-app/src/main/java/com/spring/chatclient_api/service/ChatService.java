package com.spring.chatclient_api.service;

import com.spring.chatclient_api.entity.Tut;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService {



    Flux<String> streamChat(String query);
}
