package com.blog.myblog.dao;

import com.blog.myblog.domain.Article;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.sql.Select;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ArticleRepo extends JpaRepository<Article, Long> {

    public List<Article> findByTitle(String Title);

    @Query("from Article a where a.catalog=:catalog")
    public List<Article> findAccountBycatalog(@Param("catalog") String catalog);

    @Query("from Article a where a.id=:id")
    public Optional<Article> findById(@Param("id") Long id );

    @Query("select distinct catalog from Article ")
    public List<String> findAllCatalog();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Article art set art.title=title where art.id=id")
    public void updateTitle(Long id,String title);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Article art set art.content=content where art.id=id")
    public void updateContent(Long id,String content);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Article art set art.catalog=catalog where art.id=id")
    public void updateCatalog(Long id,String catalog);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Article art set art.intro=Intro where art.id=id")
    public void updateIntro(Long id,String Intro);
}

