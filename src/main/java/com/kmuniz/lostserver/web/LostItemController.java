package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.service.LostItemService;
import com.kmuniz.lostserver.util.StaticContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.kmuniz.lostserver.util.StaticContent.PDF_TYPE;


@Controller
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("items", lostItemService.getAllLostItems());
        return "items-page";
    }

    @PostMapping("/admin/upload")
    public ResponseEntity<?> uploadLostItems(@RequestParam("file") MultipartFile file) {
        try {
            lostItemService.uploadLostItemsFromFile(file, PDF_TYPE);
            return ResponseEntity.ok(StaticContent.htmlUploadedResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/admin/upload")
    public String uploadLostItemsPage() {
        return "redirect:/upload.html";
    }

    @GetMapping("/items")
    public String getLostItems(Model model) {
        model.addAttribute("items", lostItemService.getAllLostItems());
        return "items-page";
    }

    @GetMapping("/items/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        model.addAttribute("item", lostItemService.getLostItemById(id));
        return "item-details";
    }
}
