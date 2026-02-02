package com.gaoly.aidialogue.ai;

import com.gaoly.aidialogue.ai.guardRail.GuardRail;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import reactor.core.publisher.Flux;

import java.util.List;
//护轨
@InputGuardrails({GuardRail.class})

public interface aiDialogueService {




    @SystemMessage(fromResource = "ai-System-prompt.txt")
    //使用注解@MemoryId 指定用户的历史绘画记录->UserMessage
    String chat(@MemoryId String memoryId, @UserMessage String UserMessage);






    @SystemMessage(fromResource = "ai-System-prompt-report.txt")
    Report chatForReport (@MemoryId String memoryId, @UserMessage String UserMessage);

    //语法糖,定义一个final类,里面所有属性都是final修饰并且自动重写tostring,equal这种函数
    record Report (String name, List<String> suggestionList) {};






    @SystemMessage(fromResource = "ai-System-prompt.txt")
    Result<String> chatByRAG(String UserMessage);




    //流式输出
    @SystemMessage(fromResource = "ai-System-prompt.txt")
    //使用Flux技术,动态输出实时内容
    Flux<String> chatByFlux(@MemoryId String memoryId, @UserMessage String UserMessage);
}
