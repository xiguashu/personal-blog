package com.blog.myblog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.sql.Date;

@Entity
public class Comments {
    @Id
    @GeneratedValue
    private Long id;

    private Long ArticleID;
    private String name;
    private String content;
    private Date cdate;
    private String email;

    public Comments()
    {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCdate() {
        this.cdate = new Date(System.currentTimeMillis());
    }

    public Date getCdate() {
        return cdate;
    }

    public String getName() {
        return name;
    }

    public Long getArticleID() {
        return ArticleID;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setArticleID(Long articleID) {
        ArticleID = articleID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}

