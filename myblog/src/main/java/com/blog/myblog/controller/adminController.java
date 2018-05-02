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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class adminController {
    @Autowired
    private loginservice loginser=new loginservice();
    @Autowired
    private ArticleService ArtSer=new ArticleService();
    @Autowired
    private CommentsService ComSer=new CommentsService();

    @Autowired
    private  loginservice ls;

    @RequestMapping("/login")
    String adminpage(Model model)
    {

        model.addAttribute("Account",new Account());
        return "admin/login";
    }

    @RequestMapping("/index")
    String verify(Model model)
    {

            model.addAttribute("articles",ArtSer.findAll());
            return "admin/adminpage";

    }

    @RequestMapping("adminedit")
    public String adminedit(@Param("id") Long id,Model model)
    {
        Article article= ArtSer.findbyId(id);
        model.addAttribute("article",article);
       // model.addAttribute("Articlenew",new Article());
        return "admin/adminedit";
    }

    @RequestMapping("/editArticle")
    public String editArticle(@Param("id") Long id,  Article article,Model model)
    {


        //ArtSer.update(id,article);
        ArtSer.Save(article);
        model.addAttribute("articles",ArtSer.findAll());
        return "redirect:/Article?articleId="+article.getId();
    }

    @RequestMapping("/verify")
    @ResponseBody
    public String VerifyAc(@Param("name") String name,@Param("password") String password)
    {
        Account account=new Account(name,password);
        if (loginser.verifyUser(account))
            return "T";
        return "F";
    }

    @RequestMapping("/verify2")
    public String VerifyAc2(@Param("name") String name,@Param("password") String password)
    {
        Account account=new Account(name,password);
        if (loginser.verifyUser(account))
            return "index";
        return "index";
    }

        @RequestMapping("/newArticle")
    String edit(Model model)
    {
        model.addAttribute("Article",new Article());

        return "admin/editor";
    }

    @RequestMapping("/addArticle")
    String addArticle(Model model,Article article)
    {
        ArtSer.Save(article);
        return "redirect:/Article?articleId="+article.getId();
    }
}
