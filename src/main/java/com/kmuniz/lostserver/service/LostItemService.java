package com.kmuniz.lostserver.service;

import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class LostItemService {

    @Autowired
    private LostItemRepository lostItemRepository;

    public List<LostItemEntity> getAllLostItems() {
        return lostItemRepository.findAll();
    }

    public void claimLostItem(Long userId, Long lostItemId, int quantity) {
        Claim claim = new Claim();
        claim.setUserId(userId);
        claim.setLostItemId(lostItemId);
        claim.setQuantityClaimed(quantity);
        claimRepository.save(claim);
    }

    public List<Object[]> getClaimedLostItems() {
        return claimRepository.findClaimsWithUserDetails();
    }
}
