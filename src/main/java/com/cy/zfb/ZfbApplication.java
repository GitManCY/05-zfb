package com.cy.zfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class ZfbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZfbApplication.class, args);
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
