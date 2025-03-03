package com.example.demo.controller;

import com.example.demo.dto.request.ChatMessageRequest;
import com.example.demo.dto.response.ChatMessageResponse;
import com.example.demo.service.ChatMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ContractController {

        SimpMessagingTemplate messagingTemplate;
        ChatMessageService service;


        @MessageMapping("/loadMessages")
        @SendTo("/topic/history")
        public List<ChatMessageResponse> loadChatHistory(@Payload String recipient) {
            return service.getHistoryMessage(recipient);
        }


    @MessageMapping("/chat/room/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public ChatMessage receiveMessage(@DestinationVariable String roomId, @Payload ChatMessage message) {
        System.out.println("Room ID: " + roomId);
        return message;
    }

}
