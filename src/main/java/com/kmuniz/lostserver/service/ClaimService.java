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

    public void claimItem(Long userId, Long lostItemId, int quantity) {
        LostItemEntity item = lostItemRepository.findById(lostItemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        // Check if there are enough items to claim
        if (item.getQuantity() < quantity) {
            throw new IllegalStateException("Not enough items available to claim.");
        }

        // Reduce the available quantity of the lost item
        item.setQuantity(item.getQuantity() - quantity);
        lostItemRepository.save(item);

        // Save the claim
        Claim claim = new Claim();
        claim.setUserId(userId);
        claim.setLostItemId(lostItemId);
        claim.setQuantityClaimed(quantity);
        claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}