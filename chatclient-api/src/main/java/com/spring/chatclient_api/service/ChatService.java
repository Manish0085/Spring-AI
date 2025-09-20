package com.spring.chatclient_api.service;

import com.spring.chatclient_api.entity.Tut;

import java.util.List;

public interface ChatService {

    String chat(String query);

    Tut chatToEntity(String query);

    List<Tut> chatToListEntity(String query);

    String chatTemplate();

    String creatingPromptForSpecificRole();

    String creatingPromptForSpecificRoleUsingFluentApi();

    String readingPromptsfromFile();
}
