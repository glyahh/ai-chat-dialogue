package com.gaoly.aidialogue.ai;


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
        aiDialogueService.Report report = aiDialogueService.chatForReport(String.valueOf(114514), "我是glyahh, 帮我生成一份关于ai时代是否有必要学习算法的报告");
        System.out.println(report);
    }


}
