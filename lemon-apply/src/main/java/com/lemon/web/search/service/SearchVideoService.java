package com.lemon.web.search.service;

import com.lemon.config.ConfigProperties;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.web.search.request.SearchVideoRequest;
import com.lemon.web.search.response.SearchVideoResponse;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * SearchVideoService
 *
 * @author sjp
 * @date 2019/5/23
 */
@Service
public class SearchVideoService {

	private Logger				logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private ConfigProperties	configProperties;
	@Resource
	private VideoProvider		videoProvider;

	/**
	 * 搜索视频
	 *
	 * @param request req
	 * @param response res
	 */
	public void search(SearchVideoRequest request, SearchVideoResponse response) {
		List<VideoDTO> videoDTOList = new LinkedList<>();
		String key = request.getKeyWord();
		try {
            StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
			// 索引目录对象
			Directory directory = FSDirectory.open(Paths.get(configProperties.getVideoIndexDir()));
			// 索引读取工具
			IndexReader reader = DirectoryReader.open(directory);
			// 索引搜索工具
			IndexSearcher searcher = new IndexSearcher(reader);
			// 创建查询解析器,两个参数：默认要查询的字段的名称，分词器
			QueryParser parser = new QueryParser("videoName", standardAnalyzer);
			// 创建查询对象
			Query query = parser.parse(key);
			// 最终被分词后添加的前缀和后缀处理器，默认是粗体<B></B>
			SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<font color=red>", "</font>");
			// 高亮搜索的词添加到高亮处理器中
			Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));
			// 搜索数据,两个参数：查询条件对象要查询的最大结果条数
			// 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
			TopDocs topDocs = searcher.search(query, 16);
			// 获取得分文档对象（ScoreDoc）数组.ScoreDoc中包含：文档的编号、文档的得分
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				// 取出文档编号
				int docID = scoreDoc.doc;
				// 根据编号去找文档
				Document doc = reader.document(docID);
				String text = doc.get("videoName");
				// 将查询的词和搜索词匹配，匹配到添加前缀和后缀
				TokenStream tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), docID, "videoName",
						standardAnalyzer);
				// 传入的第二个参数是查询的值
				TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, text, false, 10);
				String textValue = "";
				for (TextFragment textFragment : frag) {
					if ((textFragment != null) && (textFragment.getScore() > 0)) {
						textValue = ((textFragment.toString()));
					}
				}
				Long videoId = Long.parseLong(doc.get("videoId"));
				VideoDTO videoDTO = videoProvider.getVideo(videoId);
				videoDTO.getVideoDetailDTO().setVideoName(textValue);
				videoDTOList.add(videoDTO);
				response.setVideoDTOList(videoDTOList);
			}
		} catch (Exception e) {
			logger.error("search error->e:{}", e.getMessage());
		}
	}
}
