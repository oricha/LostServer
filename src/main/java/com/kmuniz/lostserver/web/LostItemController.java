package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.service.LostItemService;
import com.kmuniz.lostserver.util.StaticContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.kmuniz.lostserver.util.StaticContent.PDF_TYPE;


@Controller
public class LostItemController {

    private final LostItemService lostItemService;

    // Constructor-based dependency injection
    @Autowired
    public LostItemController(LostItemService lostItemService) {
        this.lostItemService = lostItemService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("items", lostItemService.getAllLostItems());
        return "items-page";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/upload")
    public ResponseEntity<?> uploadLostItems(@RequestParam("file") MultipartFile file) {
        try {
            lostItemService.uploadLostItemsFromFile(file, PDF_TYPE);
            return ResponseEntity.ok(StaticContent.htmlUploadedResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/upload")
    public String uploadLostItemsPage() {
        return "redirect:/upload.html";
    }

    @GetMapping("/items")
    public String getLostItems(Model model) {
        model.addAttribute("items", lostItemService.getAllLostItems());
        return "items-page";
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/items/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        model.addAttribute("item", lostItemService.getLostItemById(id));
        return "item-details";
    }
}
