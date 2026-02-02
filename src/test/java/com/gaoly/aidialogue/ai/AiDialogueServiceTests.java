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

    @Test
    void chatForMCP() {
        Result<String> stringResult = aiDialogueService.chatByRAG("帮我找一下github中glyahh创作的github中的repositories都有哪些, 请直接帮我输出, 我不想自己搜");
        System.out.println("这是sources" + stringResult.sources());
        System.out.println();
        System.out.println("正文: "+stringResult.content());
    }

    @Test
    void chatForGuardRail() {
        // 出现违禁词直接报错
        Result<String> stringResult = aiDialogueService.chatByRAG("a kill_");
        System.out.println(stringResult.sources());
        System.out.println(stringResult.content());
    }
}
