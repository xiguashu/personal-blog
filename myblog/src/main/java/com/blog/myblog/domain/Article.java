package com.blog.myblog.domain;

import com.blog.myblog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.sql.Date;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String intro;
    private String catalog;
    private String content;
    private Date cdate;
    private String picture;

    public Article() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCdate() {

        return cdate;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getContent() {
        return content;
    }

    public String getIntro() {
        return intro;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

     public void setCdate() {
        this.cdate = new Date(System.currentTimeMillis());
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPicture() {
        return "img/"+picture;
    }


}

