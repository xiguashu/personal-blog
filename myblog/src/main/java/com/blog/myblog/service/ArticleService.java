package com.blog.myblog.service;

import com.blog.myblog.dao.AccountRepo;
import com.blog.myblog.dao.ArticleRepo;
import com.blog.myblog.domain.Account;
import com.blog.myblog.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Service
public class ArticleService {


    @Autowired
    private ArticleRepo artRepo;

    public List<String> catalogs()
    {
        return artRepo.findAllCatalog();
    }
    public List<Article> findbyTitle(String Title)
    {
        return artRepo.findByTitle(Title);
    }
    public List<Article> findbycata(String catalog)
    {
        return artRepo.findAccountBycatalog(catalog);
    }
    public Article findbyId(Long Id)
    {
        Optional<Article> res=artRepo.findById(Id);
        return res.get();
    }

    public void delete(Long id)
    {
        artRepo.deleteById(id);
    }
    public void update(Long id,Article article)
    {
        if(article.getCatalog()!=null)
        {
            artRepo.updateContent(id,article.getCatalog());
        }
        if(article.getContent()!=null)
        {
            artRepo.updateContent(id,article.getContent());
        }
        if(article.getIntro()!=null)
        {
            artRepo.updateIntro(id,article.getIntro());
        }
        artRepo.flush();

    }

    public List<Article> findAll()
    {
        return artRepo.findAll();
    }
    public void Save(Article article)
    {
        article.setCdate();
        artRepo.save(article);
    }
}
