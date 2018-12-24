package com.kelegele.blog.service;

import com.kelegele.blog.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blog-4
 * @description: 测试
 * @author: FelixHuang
 * @create: 2018-11-26 12:01
 **/
@Service
public class TestService {

    @Autowired
    private TestDAO testDAO;

    public int getArticleCount() {
        return testDAO.getArticleCount();
    }


}
