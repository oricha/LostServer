package com.kmuniz.lostserver.service;

import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.repository.ClaimRepository;
import com.kmuniz.lostserver.repository.LostItemRepository;
import com.kmuniz.lostserver.util.FileParser;
import com.kmuniz.lostserver.util.ParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class LostItemService {

    private final LostItemRepository lostItemRepository;

    private final ClaimRepository claimRepository;

    @Autowired
    public LostItemService(LostItemRepository lostItemRepository, ClaimRepository claimRepository) {
        this.lostItemRepository = lostItemRepository;
        this.claimRepository = claimRepository;
    }

    /**
     * Uploads lost items from a given file and saves them to the repository.
     *
     * @param file     the file containing lost item data
     * @param fileType the type of the file (e.g., "pdf")
     * @throws IOException if there is an error reading the file
     */
    public void uploadLostItemsFromFile(MultipartFile file, String fileType) throws IOException {
        // Parse the file into lost item entities
        FileParser parser = ParserFactory.getParser(fileType);
        List<LostItemEntity> parsedItems = parser.parse(file);

        // Save the parsed items to the repository
        List<LostItemEntity> savedItems = lostItemRepository.saveAll(parsedItems);

        // Ensure all items were saved successfully
        validateSaveOperation(parsedItems, savedItems);
    }
    /**
     * Validates that the number of parsed items matches the number of saved items.
     *
     * @param parsedItems the list of parsed items
     * @param savedItems  the list of items saved to the repository
     */
    private void validateSaveOperation(List<LostItemEntity> parsedItems, List<LostItemEntity> savedItems) {
        if (savedItems.size() != parsedItems.size()) {
            throw new IllegalStateException("Not all items were saved successfully.");
        }
    }

    public List<LostItemEntity> getAllLostItems() {
        return lostItemRepository.findAll();
    }

    public Object getLostItemById(Long id) {
        return lostItemRepository.findById(id).orElse(null);
    }
}
