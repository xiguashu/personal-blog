package com.blog.myblog.controller;


import com.blog.myblog.domain.Account;
import com.blog.myblog.domain.Article;
import com.blog.myblog.domain.Comments;
import com.blog.myblog.service.ArticleService;
import com.blog.myblog.service.CommentsService;
import com.blog.myblog.service.loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class home {
    @Autowired
    private loginservice loginser=new loginservice();

    @Autowired
    private ArticleService ArtSer=new ArticleService();
    @Autowired
    private CommentsService ComSer=new CommentsService();

    @RequestMapping("/")
    String homepage(Model model) {
        List<Article> Articles=ArtSer.findAll();
        List<String> catalogs=ArtSer.catalogs();
        int indexofrecent=Articles.size()>7?7:Articles.size();
        Map<String,List<Comments>> ArticleComments=new HashMap<>();

        for(int i=0;i<Articles.size();i++) {
            List<Comments> commentsList = ComSer.findByArticleid(Articles.get(i).getId());
            ArticleComments.put(Articles.get(i).getId().toString(),commentsList);
        }

        model.addAttribute("ArticleComments",ArticleComments);
        model.addAttribute("recentposts", Articles.subList(Articles.size()-indexofrecent,Articles.size()));
        model.addAttribute("catalogs",catalogs);
        model.addAttribute("Articles",Articles);
        return "index";
    }


    @RequestMapping("/about")
    public String ToAbout(Model model)
    {
        List<Article> Articles=ArtSer.findAll();
        int indexofrecent=Articles.size()>5?5:Articles.size();

        model.addAttribute("recentposts", Articles.subList(Articles.size()-indexofrecent,Articles.size()));
        return "about";
    }
    @RequestMapping("/contact")
    String ToContect(Model model)
    {
        List<Article> Articles=ArtSer.findAll();
        int indexofrecent=Articles.size()>5?5:Articles.size();
        model.addAttribute("recentposts", Articles.subList(Articles.size()-indexofrecent,Articles.size()));
        return "contact";
    }

    @RequestMapping("/catalog")
    public String catalogs(@Param("id") String id,Model model)
    {
        List<Article> Articles=ArtSer.findbycata(id);
        List<String> catalogs=ArtSer.catalogs();
        List<Article> AllArticles=ArtSer.findAll();
        int indexofrecent=AllArticles.size()>5?5:AllArticles.size();

        Map<String,List<Comments>> ArticleComments=new HashMap<>();
        for(int i=0;i<Articles.size();i++) {
            List<Comments> commentsList = ComSer.findByArticleid(Articles.get(i).getId());
            ArticleComments.put(Articles.get(i).getId().toString(),commentsList);
        }

        model.addAttribute("ArticleComments",ArticleComments);

        model.addAttribute("recentposts", AllArticles.subList(AllArticles.size()-indexofrecent,AllArticles.size()));
        model.addAttribute("catalogid",id);
        model.addAttribute("catalogs",catalogs);
        model.addAttribute("Articles",Articles);
        return "catalog";
    }
}
