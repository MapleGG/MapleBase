package com.scd.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
	
    /** 基于@ExceptionHandler异常处理 
     * 统一处理某一类异常，从而能够减少代码重复率和复杂度*/  
    @ExceptionHandler  
    public String exp(HttpServletRequest request, Exception ex,Model model) {     
    	
    	model.addAttribute("ex",ex.getMessage());
        request.setAttribute("ex", ex.getMessage());          
        return "error";
    } 

}
