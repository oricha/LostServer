package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.service.LostItemService;
import com.kmuniz.lostserver.util.StaticContent;
import com.kmuniz.lostserver.web.requestResponse.ClaimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
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
    public List<LostItemEntity> getLostItems() {
        return lostItemService.getAllLostItems();
    }

    @PostMapping("/claim")
    public ResponseEntity<?> claimLostItem(@RequestBody ClaimRequest claimRequest) {
        lostItemService.claimLostItem(claimRequest.getUserId(), claimRequest.getLostItemId(), claimRequest.getQuantityClaimed());
        return ResponseEntity.ok("Item claimed successfully");
    }

    @GetMapping("/admin/claims")
    public List<Object[]> getClaimedLostItems() {
        return lostItemService.getClaimedLostItems();
    }
}
