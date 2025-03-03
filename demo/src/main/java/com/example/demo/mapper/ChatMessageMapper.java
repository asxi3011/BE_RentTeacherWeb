package com.example.demo.mapper;

import com.example.demo.dto.request.ChatMessageRequest;
import com.example.demo.dto.response.ChatMessageResponse;
import com.example.demo.entity.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    ChatMessage toChatMessage(ChatMessageRequest request);

    ChatMessageResponse toResponse(ChatMessage chatMessage);

    List<ChatMessageResponse> toListResponse(List<ChatMessage> listChatMessage);


}
