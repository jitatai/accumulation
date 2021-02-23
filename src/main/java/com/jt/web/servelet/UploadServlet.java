package com.jt.web.servelet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/upload")
@Slf4j
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameParam = request.getParameter("name");
        String ageParam = request.getParameter("age");

        log.info("GET请求 name:{}, age:{}", nameParam, ageParam);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameParam = request.getParameter("name");
        String ageParam = request.getParameter("age");

        // 根据参数名获取文件
        String fileParam = request.getParameter("file");

        log.info("POST请求 name:{}, age:{}, file:{}", nameParam, ageParam,fileParam);
    }
}