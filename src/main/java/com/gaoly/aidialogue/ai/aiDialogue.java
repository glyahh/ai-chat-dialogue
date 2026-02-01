package com.gaoly.aidialogue.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class aiDialogue {
    @Resource
    private ChatModel qwenChatModel;

    public String chat (String Message) {
        UserMessage userMessage = new UserMessage(Message);
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        log.info("ai输出 -> {}", aiMessage.toString());
        return aiMessage.text();
    }
}
