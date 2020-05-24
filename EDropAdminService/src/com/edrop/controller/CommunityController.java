package com.edrop.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

}
