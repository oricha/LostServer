package com.kmuniz.lostserver.service;

import com.kmuniz.lostserver.data.Claim;
import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.repository.ClaimRepository;
import com.kmuniz.lostserver.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private LostItemRepository lostItemRepository;


    public Claim getClaimById(Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }


    public void claimItem(Long id) {
        LostItemEntity item = lostItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found"));
        // Logic for processing the claim (e.g., mark it as claimed)
        item.setQuantity(item.getQuantity() - 1); // Reduce the quantity
        lostItemRepository.save(item);
    }
}