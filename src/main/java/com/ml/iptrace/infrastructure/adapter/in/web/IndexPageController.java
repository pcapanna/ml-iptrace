package com.ml.iptrace.infrastructure.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class IndexPageController {
    @Controller
    public class IndexController {
        @RequestMapping("/")
        public String index() {
            return "Spring Boot App";
        }
    }

}
