package com.ecommerce.ai_service.controller;

import com.ecommerce.ai_service.client.ProductClient;
import com.ecommerce.ai_service.dto.ChatRequest;
import com.ecommerce.ai_service.dto.ChatResponse;
import com.ecommerce.ai_service.dto.ProductDto;
import com.ecommerce.ai_service.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request){
        return new ChatResponse(aiService.chat(request.getMessage()));
    }

}
