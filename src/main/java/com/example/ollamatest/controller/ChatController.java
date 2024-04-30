package com.example.ollamatest.controller;

import com.example.ollamatest.dto.ChatDto;
import com.example.ollamatest.dto.CustomMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    final private OllamaChatClient chatClient;

    @PostMapping(value = "/generate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> generate(@RequestBody ChatDto chatDto) {
        Prompt prompt = new Prompt(chatDto.getChatObjectList().stream().map(CustomMessage::new).collect(Collectors.toList()));
        return chatClient.stream(prompt);
    }
}
