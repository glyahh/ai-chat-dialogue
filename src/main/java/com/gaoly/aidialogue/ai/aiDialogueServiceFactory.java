package com.gaoly.aidialogue.ai;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class aiDialogueServiceFactory {

    @Resource
    private QwenChatModel qwenChatModel;

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private McpToolProvider mcpToolProvider;

    @Resource(name = "MyQwenChatModel")
    private ChatModel myQwenModel;

    @Resource
    private StreamingChatModel streamingChatModel;

//    @Bean
//    public aiDialogueService createAiDialogueService() {
//        /**
//         * 利用反射获取method生成一个实现aiDialogueService接口的代理对象
//         * 代理对象将调用大模型,并且将大模型返回的Aimessage类型转化成string返回给调用方
//         */
//        return AiServices.create(aiDialogueService.class, qwenChatModel);
//    }


    @Bean
    public aiDialogueService createAiDialogueService_menory() {
        //创建一个Memory,缓存最近10条对话
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        aiDialogueService aiDialogueService = AiServices.
                //服务的对象 (我这个工厂给哪个类提供服务)
                builder(aiDialogueService.class).
                //模型与输出模型
                chatModel(myQwenModel).
                streamingChatModel(streamingChatModel). // 流式输出

                chatMemory(chatMemory).
                //根据memoryId创建Memory,隔离用户之间的历史会话
                chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10)).

                contentRetriever(contentRetriever). // RAG 检索增强生成
                toolProvider(mcpToolProvider). // MCP 工具
                build();

        return aiDialogueService;
    }
}
