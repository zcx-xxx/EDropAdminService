package com.edrop.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.edrop.mapper.ArticleMapper;
import com.edrop.mapper.UserArticleIsLikeMapper;
import com.edrop.pojo.Article;
import com.edrop.pojo.User;
import com.edrop.service.ArticleService;
import com.edrop.utils.HuffmanCodeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private HuffmanCodeUtil huffmanCodeUtil;
	@Resource 
	private UserArticleIsLikeMapper userArticleIsLikeMapper; 
	@Override
	public Integer addArticle(Article article) {
		HuffmanCodeUtil.HuffmanCodeResult result = huffmanCodeUtil.zipFile(article.getContent());
		System.out.println(article.getArticleTitle()+article.getArticleTitle());
		Integer idx = articleMapper.addArticle(article.getArticleTitle(),article.getArticleDescription(),result.getHuffmanBytes(), toByteArray(result.getHuffmanCodes()),article.getPublishDate());
		return idx;
	}
	
	public Integer addArticleTest(String content) {
		HuffmanCodeUtil.HuffmanCodeResult result = huffmanCodeUtil.zipFile(content);
		Integer idx = articleMapper.addArticleTest(result.getHuffmanBytes(), toByteArray(result.getHuffmanCodes()));
		return idx;
	}
	
	// 将 Map 对象序列化为字节数组
	public byte[] toByteArray(Map<Byte, String> huffmanCodes) {
		byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(huffmanCodes);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
	}
	
	// 将字节数组反序列化为 Object 对象
    public Object toObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    } 

	@Override
	public String selectArticleById(Integer id) {
		Article article = articleMapper.selectArticleById(id);
		// 反序列化出编码表
		Object obj = toObject(article.getDecodeTable());
		Map<Byte, String> huffmanCodes = (Map<Byte, String>)obj;
		// 解码
		String ans = HuffmanCodeUtil.unZipFile(huffmanCodes, article.getContentBytes());
		System.out.println(ans.length());
		return ans;
	}
	@Override
	public String getArticleBriefInfo(Integer userId, Integer pageNum, Integer pageSize) {
		// 查询文章简要信息
		List<Article> list = articleMapper.selectArticleBriefInfo((pageNum - 1) * pageSize, pageSize);
		// 报存需要返回的结果
		JSONObject ans = new JSONObject();
		JSONArray array = new JSONArray();
		for (Article item : list) {
			Integer idx = item.getId();
			// 查询该用户是否点赞 并封装
			Integer res = userArticleIsLikeMapper.selectUserArticleIsExits(userId, idx);
			JSONObject obj = new JSONObject(); 
			if (res == 1) {
				obj.put("is_like", "1");
			} else {
				obj.put("is_like", "0");
			}
			obj.put("article_id", idx);
			obj.put("publish_date", item.getPublishDate() == null ? "" : item.getPublishDate());
			obj.put("article_description", item.getArticleDescription() == null ? "" : item.getArticleDescription());
			obj.put("like_counts", item.getArticleLikeCount() == null ? "0" : item.getArticleLikeCount());
			obj.put("comment_counts", item.getArticleCommentCount() == null ? "0" : item.getArticleCommentCount());
			// 数组中追加元素
			array.put(obj);
		}
		// 封装数组
//		ans.append("articles", array);
		ans.put("articles", array);
		// 返回结果
		return ans.toString();
	}

	@Override
	public Integer likeOrCancelLike(Integer userId, Integer articleId) {
		Integer count = userArticleIsLikeMapper.selectUserArticleIsExits(userId, articleId);
		Integer state = 0;
		if (count == 0) {
			state = userArticleIsLikeMapper.insertItem(userId, articleId);
			articleMapper.updateLikeCounts(articleId, 1);
		} else {
			state = userArticleIsLikeMapper.deleteItem(userId, articleId);
			articleMapper.updateLikeCounts(articleId, -1);
		}
		return state;
	}

	@Override
	public PageInfo<Article> getAllArticleByPage(Integer page, Integer size) {
		PageHelper.startPage(page,size);
		
		List<Article> articles =  articleMapper.selectAllArticle();
		PageInfo<Article> info = new PageInfo<Article>(articles);
		return info;
	}

	@Override
	public void deleteArticleById(Integer articleId) {
		articleMapper.deleteArticleById(articleId);
		return;
	}
}
