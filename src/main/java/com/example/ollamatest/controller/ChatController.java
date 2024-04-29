package com.example.ollamatest.controller;

import com.example.ollamatest.dto.ChatDto;
import com.example.ollamatest.dto.CustomMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    final private Sinks.Many<ChatResponse> chatSink = Sinks.many().multicast().onBackpressureBuffer();

    final private OllamaChatClient chatClient;

    @PostMapping("/generate")
    public Flux<ChatResponse> generate(@RequestBody ChatDto chatDto) {
        Prompt prompt = new Prompt(chatDto.getChatObjectList().stream().map(CustomMessage::new).collect(Collectors.toList()));
        return chatClient.stream(prompt).doOnNext(chatSink::tryEmitNext);
    }

    @GetMapping("/stream")
    public Flux<ChatResponse> generateStream() {
        return chatSink.asFlux()
                .doOnCancel(() -> {
                    chatSink.asFlux().blockLast();
                });
    }
}
