package com.gaoly.aidialogue.ai.Model;

import com.gaoly.aidialogue.ai.Linsters.MyQwenModelLinstener;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "langchain4j.community.dashscope.chat-model")
@Data
public class MyQwenModelConfig {
    private String apiKey;
    private String modelName;

    @Resource
    private ChatModelListener chatModelListener;

    @Bean("MyQwenChatModel")
    public ChatModel qwenChatModel() {
        return QwenChatModel.builder()
                .listeners(List.of(chatModelListener))
                .apiKey(apiKey)
                .modelName(modelName)
                .build();
    }
}
