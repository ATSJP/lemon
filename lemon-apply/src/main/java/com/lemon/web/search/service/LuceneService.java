package com.lemon.web.search.service;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * @author sys
 * @date 2019/4/14
 **/
@Component
public class LuceneService {

	/**
	 * 定义分词器
	 */
	private static final Analyzer ANALYZER = new IKAnalyzer();

	/**
	 * 根据关键词创建
	 * 
	 * @param videoId 视频id
	 * @param videoName 视频名称
	 * @param videoContext 视频简介
	 * @return Document doc
	 */
	public Document createDocument(Long videoId, String videoName, String videoContext) {
		// 创建Document对象
		Document doc = new Document();
		// 获取每列数据
		// tips: StoredField 会储存，但不是被建立索引 StringField 会建立索引，但不会分词，TextField 会建立索引，也会分词
		// Field videoNameField = new StringField("videoName", videoName, Field.Store.YES);
		// Field videoContextNameField = new StringField("videoContext", videoContext, Field.Store.NO);
		Field videoNameField = new Field("videoName", videoName, TextField.TYPE_STORED);
		Field videoContextNameField = new Field("videoContext", videoContext, TextField.TYPE_STORED);
		// Field textField = new StringField("text", text, Field.Store.YES);
		Field textField = new StoredField("videoId", String.valueOf(videoId));
		// 添加到Document中
		doc.add(videoNameField);
		doc.add(videoContextNameField);
		doc.add(textField);
		// 返回doc
		return doc;
	}

	/**
	 * 封裝一个方法，用于将数据库中的数据解析为一个个关键字词存储到索引文件中
	 *
	 * @param docs docs
	 * @param path path
	 */
	public void write(Collection<Document> docs, String path) throws IOException {
		// 索引库的存储目录
		Directory directory = FSDirectory.open(Paths.get(path));
		// 关联当前lucene版本和分值器
		IndexWriterConfig config = new IndexWriterConfig(ANALYZER);
		// 设置打开方式：OpenMode.APPEND 会在索引库的基础上追加新索引。OpenMode.CREATE会先清空原来数据，再提交新的索引
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		// 传入目录和分词器
		IndexWriter indexWriter = new IndexWriter(directory, config);
		// 写入到目录文件中
		indexWriter.addDocuments(docs);
		// 提交事务
		indexWriter.commit();
		// 关闭流
		indexWriter.close();
	}

}
