package com.blog.myblog.controller;


import com.blog.myblog.domain.Account;
import com.blog.myblog.domain.Article;
import com.blog.myblog.domain.Comments;
import com.blog.myblog.service.ArticleService;
import com.blog.myblog.service.CommentsService;
import com.blog.myblog.service.loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class blogController {

    @Autowired
    private ArticleService ArtSer=new ArticleService();
    @Autowired
    private CommentsService cs;
    private Model model;

    @RequestMapping("/Article")
    String findbyid(Model model,@RequestParam("articleId") Long articleId) {
        Article article = ArtSer.findbyId(articleId);
        model.addAttribute("article", article);
        model.addAttribute("Comments",new Comments());
        List<Comments> commentsList=cs.findByArticleid(articleId);
        List<Article> Articles=ArtSer.findAll();
        int indexofrecent=Articles.size()>7?7:Articles.size();
         model.addAttribute("recentposts", Articles.subList(Articles.size()-indexofrecent,Articles.size()));
        model.addAttribute("commentsList",commentsList);

         List<String> catalogs=ArtSer.catalogs();
         model.addAttribute("catalogs",catalogs);
        return "article";
    }

    @RequestMapping("/addComment")
    public String addComment(Model model, Comments c,@RequestParam("articleId") Long articleId)
    {
        c.setArticleID(articleId);
        c.setCdate();
        cs.addComment(c);
        this.model = model;
        return "redirect:/Article?articleId="+articleId;
    }


}
