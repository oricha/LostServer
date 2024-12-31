package com.kmuniz.lostserver.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.kmuniz.lostserver.data.Claim;
import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.repository.ClaimRepository;
import com.kmuniz.lostserver.repository.LostItemRepository;
import com.kmuniz.lostserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

public class ClaimServiceTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private LostItemRepository lostItemRepository;

    @Mock
    private UserRepository userRepository;

    private ClaimService claimService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        claimService = new ClaimService(claimRepository, lostItemRepository, userRepository);
    }

    @Test
    void testClaimItem_UserNotFound() {
        Long userId = 1L;
        Long lostItemId = 1L;
        int quantity = 1;

        // Mock user repository to return false when checking for user existence
        when(userRepository.existsById(userId)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            claimService.claimItem(userId, lostItemId, quantity);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testClaimItem_ItemNotFound() {
        Long userId = 1L;
        Long lostItemId = 1L;
        int quantity = 1;

        // Mock user repository to return true for user existence
        when(userRepository.existsById(userId)).thenReturn(true);

        // Mock lost item repository to return empty
        when(lostItemRepository.findById(lostItemId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            claimService.claimItem(userId, lostItemId, quantity);
        });

        assertEquals("Item not found", exception.getMessage());
    }

    @Test
    void testClaimItem_NotEnoughItems() {
        Long userId = 1L;
        Long lostItemId = 1L;
        int quantity = 10;

        // Mock user repository to return true for user existence
        when(userRepository.existsById(userId)).thenReturn(true);

        // Mock lost item repository to return a lost item with insufficient quantity
        LostItemEntity item = new LostItemEntity();
        item.setQuantity(5);  // Less than the claimed quantity
        when(lostItemRepository.findById(lostItemId)).thenReturn(Optional.of(item));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            claimService.claimItem(userId, lostItemId, quantity);
        });

        assertEquals("Not enough items available to claim.", exception.getMessage());
    }

    @Test
    void testClaimItem_Success() {
        Long userId = 1L;
        Long lostItemId = 1L;
        int quantity = 1;

        // Mock user repository to return true for user existence
        when(userRepository.existsById(userId)).thenReturn(true);

        // Mock lost item repository to return an item with enough quantity
        LostItemEntity item = new LostItemEntity();
        item.setQuantity(10);
        when(lostItemRepository.findById(lostItemId)).thenReturn(Optional.of(item));

        // Mock saving the updated lost item and the claim
        when(lostItemRepository.save(any(LostItemEntity.class))).thenReturn(item);
        Claim claim = new Claim();
        claim.setUserId(userId);
        claim.setLostItemId(lostItemId);
        claim.setQuantityClaimed(quantity);
        when(claimRepository.save(any(Claim.class))).thenReturn(claim);

        // Test the claimItem method
        claimService.claimItem(userId, lostItemId, quantity);

        verify(claimRepository).save(any(Claim.class));
        verify(lostItemRepository).save(any(LostItemEntity.class));
    }
}