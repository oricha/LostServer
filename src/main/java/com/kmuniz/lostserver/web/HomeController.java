package com.kmuniz.lostserver.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String uploadPage() {
        return "redirect:/upload.html";
    }
}
