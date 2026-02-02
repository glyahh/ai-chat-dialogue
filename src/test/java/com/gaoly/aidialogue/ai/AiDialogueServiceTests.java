package com.gaoly.aidialogue.ai;


import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiDialogueServiceTests {

    @Resource
    private aiDialogueService aiDialogueService;

    @Test
    public void chat (){
        String result = aiDialogueService.chat(String.valueOf(114514), "我是glyahh, 24岁, 事学生罢");
        System.out.println(result);
        result = aiDialogueService.chat(String.valueOf(114515), "我是谁来着,我会什么, 你擅长什么");
        System.out.println(result);
    }

    @Test
    void chatForReport() {
        aiDialogueService.Report report = aiDialogueService.chatForReport(String.valueOf(114514),
                "我是glyahh,学了一年半编程,主要功课ai-agent开发,javaweb结合大模型开发,全栈工程师,算法竞赛者,给我一点接下来学习的建议");
        System.out.println(report);
    }

    @Test
    void chatForRAG() {
        Result<String> stringResult = aiDialogueService.chatByRAG("我是一个编程小白,帮我推荐一个人叫我编程, 叫glyahh的人怎么样");
        System.out.println(stringResult.sources());
        System.out.println(stringResult.content());
    }
}
