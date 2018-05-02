package com.blog.myblog.dao;

import com.blog.myblog.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {

    public List<Account> findByName(String name);

    public List<Account> findByNameAndPassword(String name,String password);

    @Query("from Account u where u.name=:name")
    public List<Account> findUser(@Param("name") String name);

}

