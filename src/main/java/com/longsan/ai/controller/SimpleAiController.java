package com.longsan.ai.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleAiController {

    @GetMapping("/ai/simple")
    public String completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message,
                             @RequestParam(value = "model", defaultValue = "qwen2:7b", required = false) String model) {
        ChatModel chatModel = new OllamaChatModel(new OllamaApi());
        ChatResponse response = chatModel.call(
                new Prompt(
                        message,
                        OllamaOptions.create()
                                .withModel(model)
                                .withTemperature(0.8f)
                                .withNumGPU(1)
                ));
        String content = response.getResult().getOutput().getContent();
        return content;

    }
}
