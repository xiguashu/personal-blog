package com.blog.myblog.service;

import com.blog.myblog.dao.AccountRepo;
import com.blog.myblog.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class loginservice {


    @Autowired
    private AccountRepo accountRepo;

    public boolean verifyUser(Account account) {

        if (accountRepo.findByNameAndPassword(account.getName(), account.getPassword()).isEmpty()) {
            return false;
        } else {
            return true;
        }

    }
        public String registerUser(Account user) {

        if (accountRepo.findByName(user.getName()).isEmpty()) {
            accountRepo.save(user);
            return "用户名  " + user.getName() + " 注册成功";

        } else {

            return "用户名 " + user.getName() + "已被占用！";
        }
//
    }
}
