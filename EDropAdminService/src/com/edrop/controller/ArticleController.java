package com.edrop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edrop.pojo.Article;
import com.edrop.pojo.User;
import com.edrop.service.ArticleService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleService articleServiceImpl;
	
	@RequestMapping("/load_article_list")
	public ModelAndView  userList(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Article> pageInfo = articleServiceImpl.getAllArticleByPage(page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("article_list");
		return modelAndView;
	}
	
	@RequestMapping("/delete_article")
	public ModelAndView delete(String articleId,
			@RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size) {
		System.out.println("文章 id：" + articleId);
		articleServiceImpl.deleteArticleById(Integer.valueOf(articleId));
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<Article> pageInfo = articleServiceImpl.getAllArticleByPage(page,size);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("article_find");
		return modelAndView;
	}
}
