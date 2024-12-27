package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.service.LostItemService;
import com.kmuniz.lostserver.web.requestResponse.ClaimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LostItemController {

    @Autowired
    private LostItemService lostItemService;


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
