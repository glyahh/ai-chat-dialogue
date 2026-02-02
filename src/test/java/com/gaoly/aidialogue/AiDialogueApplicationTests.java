package com.gaoly.aidialogue;

import com.gaoly.aidialogue.ai.aiDialogue;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiDialogueApplicationTests {
    @Resource
    private aiDialogue aiDialogue;
    @Test
    void chat() {
        aiDialogue.chat("你好,我是glyahh");
    }

    @Test
    void chatByyMessage() {
        UserMessage userMessage = UserMessage.from(
            TextContent.from("描述图片"),
            ImageContent.from("https://www.codefather.cn/logo.png")
        );
        aiDialogue.chating(userMessage);
    }



}
