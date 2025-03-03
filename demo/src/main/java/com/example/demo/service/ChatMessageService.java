package com.example.demo.service;

import com.example.demo.dto.request.ChatMessageRequest;
import com.example.demo.dto.response.ChatMessageResponse;
import com.example.demo.entity.ChatMessage;
import com.example.demo.mapper.ChatMessageMapper;
import com.example.demo.repository.ChatMessageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ChatMessageService {

    ChatMessageRepository repository;
    ChatMessageMapper mapper;

    public ChatMessageResponse saveMessage(ChatMessageRequest request){
        ChatMessage chatMessage = mapper.toChatMessage(request);
        return mapper.toResponse(repository.save(chatMessage));
    }

    public List<ChatMessageResponse> getHistoryMessage(String username){
        var context = SecurityContextHolder.getContext().getAuthentication();
        List<ChatMessage> listChat = repository.findAllBySenderAndRecipient(context.getName(),username);
       List<ChatMessage> listChatOpposite = repository.findAllBySenderAndRecipient(username,context.getName());
        listChat.addAll(listChatOpposite);
        listChat.sort(Comparator.comparing(ChatMessage::getTimestamp));

        return mapper.toListResponse(listChat);
    }

}
