package com.blog.myblog.dao;

import com.blog.myblog.domain.Article;
import com.blog.myblog.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comments, Long> {

    @Query("from Comments c where c.ArticleID=:ArticleID")
    public List<Comments> findByArticleID(@Param("ArticleID") Long ArticleID);

}

