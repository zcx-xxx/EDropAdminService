package com.edrop.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edrop.mapper.ArticleMapper;
import com.edrop.pojo.Article;
import com.edrop.service.ArticleService;
import com.edrop.utils.HuffmanCodeUtil;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private HuffmanCodeUtil huffmanCodeUtil;
	@Override
	public Integer addArticle(String content) {
		HuffmanCodeUtil.HuffmanCodeResult result = huffmanCodeUtil.zipFile(content);
		Integer idx = articleMapper.addArticle(result.getHuffmanBytes(), toByteArray(result.getHuffmanCodes()));
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
		return ans;
	}
}
