package com.gaoly.aidialogue.ai.RAG;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.IngestionResult;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Resource
    private EmbeddingModel QwenembeddingModel;

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @Bean
    public ContentRetriever contentRetriever() {
        // 1. 加载文档
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/MyRAG.txt");

        // 2. 文档切割, 把每个文档按照段落切割成多个段落
        // 每段最多1000字,上下两端重叠200字, 以防段落分割过于严重
        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(500, 200);

        // 3. 自定义文档加载器，把文档转换成向量并保存到向量数据库中
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter)
                // 为了提高文档的质量，为每个切割后的文档碎片 TextSegment 添加文档名称作为元信息
                // 保留原本的元数据,给文本数据添加元数据中的标题
                // 元数据指文档的标题,作者,创建时间等等
                .textSegmentTransformer(textSegment -> {
                    return TextSegment.from(
                            textSegment.metadata().getString("file_name") + "\n" + textSegment.text(), textSegment.metadata()
                    );
                })
                .embeddingModel(QwenembeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(document);

        // 4. 自定义内容加载器
        EmbeddingStoreContentRetriever build = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(QwenembeddingModel)
                .maxResults(5) // 最多返回5个结果
                .minScore(0.75) // 最低阈值,匹配度的阈值,分数越高文档越少
                .build();

        return build;
    }
}
