package com.example.demo.controller;

import com.example.demo.dto.request.ChatMessageRequest;
import com.example.demo.dto.request.LoadMessageRequest;
import com.example.demo.dto.response.ChatMessageResponse;
import com.example.demo.dto.response.UserCreationResponse;
import com.example.demo.entity.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import com.example.demo.service.ChatMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ChatController {

    SimpMessagingTemplate messagingTemplate;
    ChatMessageService service;

    @MessageMapping("/chatUser")
    public void sendPrivateMessage(@Payload ChatMessageRequest request) {

        request.setTimestamp(LocalDateTime.now());
        service.saveMessage(request);
        messagingTemplate.convertAndSendToUser(request.getRecipient(), "/messages", request);
    }

    @MessageMapping("/loadMessages")
    @SendTo("/topic/contract")
    public List<ChatMessageResponse> loadChatHistory(@Payload String recipient) {
        return service.getHistoryMessage(recipient);
    }
}