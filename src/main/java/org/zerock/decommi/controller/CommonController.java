package org.zerock.decommi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
public class CommonController {
    @RequestMapping({ "", "/" })
    public String index() {
        return "index";
    }
}
