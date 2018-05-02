package com.blog.myblog.service;

import com.blog.myblog.dao.AccountRepo;
import com.blog.myblog.dao.ArticleRepo;
import com.blog.myblog.dao.CommentRepo;
import com.blog.myblog.domain.Account;
import com.blog.myblog.domain.Article;
import com.blog.myblog.domain.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentsService {


    @Autowired
    private CommentRepo commentRepo;

    public List<Comments> findByArticleid(Long id)
    {
        return commentRepo.findByArticleID(id);
    }

    public void addComment(Comments c)
    {
        commentRepo.save(c);
    }
}
