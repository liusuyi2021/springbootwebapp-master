package org.synjones.springframework.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    void index(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	resp.sendRedirect("/products");
    	//return "index";
    }
}
