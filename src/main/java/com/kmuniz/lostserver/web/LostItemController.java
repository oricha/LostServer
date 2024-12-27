package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.service.LostItemService;
import com.kmuniz.lostserver.util.StaticContent;
import com.kmuniz.lostserver.web.requestResponse.ClaimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;

    @PostMapping("/admin/upload")
    public ResponseEntity<?> uploadLostItems(@RequestParam("file") MultipartFile file) {
        try {
            lostItemService.uploadLostItemsFromFile(file);
            return ResponseEntity.ok(StaticContent.htmlUploadedResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
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

    @PostMapping("/claim")
    public ResponseEntity<?> claimLostItem(@RequestBody ClaimRequest claimRequest) {
        lostItemService.claimLostItem(claimRequest.getUserId(), claimRequest.getLostItemId(), claimRequest.getQuantityClaimed());
        return ResponseEntity.ok("Item claimed successfully");
    }

    @GetMapping("/admin/claims")
    public String getClaimedLostItems(Model model) {
        model.addAttribute(lostItemService.getClaimedLostItems());
        return "claims-page";
    }
}
