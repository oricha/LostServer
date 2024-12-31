package com.kmuniz.lostserver.service;

import com.kmuniz.lostserver.data.Claim;
import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.repository.ClaimRepository;
import com.kmuniz.lostserver.repository.LostItemRepository;
import com.kmuniz.lostserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final LostItemRepository lostItemRepository;
    private final UserRepository userRepository;

    // Constructor-based injection
    public ClaimService(ClaimRepository claimRepository, LostItemRepository lostItemRepository, UserRepository userRepository) {
        this.claimRepository = claimRepository;
        this.lostItemRepository = lostItemRepository;
        this.userRepository = userRepository;
    }

    public Claim getClaimById(Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    public void claimItem(Long userId, Long lostItemId, int quantity) {

        // Check if the user exists
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalArgumentException("User not found");
        }
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
        claim.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public List<Object[]> getClaimedLostItems() {
        return claimRepository.findClaimsWithUserDetails();
    }
}