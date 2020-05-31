package com.edrop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edrop.dto.FileDTO;
import com.edrop.pojo.Article;
import com.edrop.service.ArticleService;
import com.edrop.utils.UCloudProvider;

@Controller
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
    private UCloudProvider uCloudProvider;
	@Autowired
    private ArticleService articleService;
	
    @RequestMapping("/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
    	System.out.println("test1");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        try {
            String fileName = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            System.out.println(fileName);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileName);
            return fileDTO;
        } catch (Exception e) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("发表失败");
            return fileDTO;
        }
    }
    
    @RequestMapping("/add")
    @ResponseBody
    public String addArticle(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "content",required = false) String content,
            @RequestParam(value = "id",required = false)Integer id,
            HttpServletRequest request, Model model) {
//    	System.out.println(request.getCharacterEncoding());
//    	System.out.println("content: " + content);
        Article article = new Article();
        article.setArticleTitle(title);
        article.setArticleDescription(description);
        article.setContent(content);
        article.setId(id);
        Timestamp time = new Timestamp(new Date().getTime());  
        article.setPublishDate(time);
        articleService.addArticle(article);
        return "success";
    }
    /**
     * @Title: getArticleBriefInfo   
     * @Description: TODO 获取文章简要信息
     * @param: @param userId
     * @param: @param pageNum
     * @param: @param pageSize
     * @param: @return      
     * @return: String      
     * @throws
     */
//    @ResponseBody
    @RequestMapping("/get_article_brief_info")
    public void getArticleBriefInfo(String userId, String pageNum, String pageSize, 
    		HttpServletResponse response) {
    	String ans = articleService.getArticleBriefInfo(Integer.valueOf(userId), Integer.valueOf(pageNum), 
    			Integer.valueOf(pageSize));
    	if (ans == null) ans = "";
    	try {
			response.getWriter().write(ans);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return;
    }
    /**
     * 
     * @Title: getArticleDetailInfo   
     * @Description: TODO 获取文章详细信息   
     * @param: @param articleId
     * @param: @return      
     * @return: String      
     * @throws
     */
//    @ResponseBody
//    @ResponseBody
    @RequestMapping("/get_article_detail_info")
    public void getArticleDetailInfo(String articleId, HttpServletResponse response) {
    	response.setCharacterEncoding("UTF_8");
    	String ans = articleService.selectArticleById(Integer.valueOf(articleId));
    	if (ans == null) ans = "";
    	JSONObject json = new JSONObject();
    	json.put("content", ans);
    	try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return;
    }
//    /community/like_or_cancel_like
    @RequestMapping("like_or_cancel_like")
    public void likeOrCancelLike(String userId, String articleId, HttpServletResponse response) {
    	Integer ans = 0;
    	Integer state = articleService.likeOrCancelLike(Integer.valueOf(userId), Integer.valueOf(articleId));
    	if (state == 1) ans = 1;
    	JSONObject json = new JSONObject();
    	json.put("state", ans);
    	try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
