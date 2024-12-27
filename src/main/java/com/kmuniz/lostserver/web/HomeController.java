package com.kmuniz.lostserver.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String uploadPage() {
        return "redirect:/upload.html";
    }
}
