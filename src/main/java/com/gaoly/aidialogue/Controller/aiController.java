package com.gaoly.aidialogue.Controller;

import com.gaoly.aidialogue.ai.aiDialogueService;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class aiController {

    @Resource
    private aiDialogueService aiDialogueService;

    @GetMapping("/chat")
    public Flux<ServerSentEvent<String>> chat (int memoryId, String message) {
        return aiDialogueService.chatByFlux(String.valueOf(memoryId), message)
            .map(
                // 给泛型静态方法 builder() 显式指定类型参数, 返回String类型的ServerSentEvent
            i -> ServerSentEvent.<String>builder()
                    .data(i)
                    .build()
            );
    }
}
