package com.gaoly.aidialogue;

import com.gaoly.aidialogue.ai.aiDialogue;
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
        System.out.println(aiDialogue.chat("你好,我是glyahh"));
    }
}
