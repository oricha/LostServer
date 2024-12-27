package com.kmuniz.lostserver.service;

import com.kmuniz.lostserver.data.Claim;
import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.repository.ClaimRepository;
import com.kmuniz.lostserver.repository.LostItemRepository;
import com.kmuniz.lostserver.util.PdfParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class LostItemService {

    @Autowired
    private LostItemRepository lostItemRepository;

    @Autowired
    private ClaimRepository claimRepository;

    public void uploadLostItemsFromFile(MultipartFile file) throws IOException {
        List<LostItemEntity> items = PdfParser.parseLostItems(file);
        List<LostItemEntity> savedItems =lostItemRepository.saveAll(items);
        // Verify the number of saved items matches the number of parsed items
        if (savedItems.size() != items.size()) {
            throw new IllegalStateException("Not all items were saved successfully.");
        }
    }

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
