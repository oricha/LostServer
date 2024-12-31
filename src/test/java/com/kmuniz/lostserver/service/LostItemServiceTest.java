package com.kmuniz.lostserver.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.repository.ClaimRepository;
import com.kmuniz.lostserver.repository.LostItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LostItemServiceTest {

    @Mock
    private LostItemRepository lostItemRepository;

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private MultipartFile file;

    private LostItemService lostItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lostItemService = new LostItemService(lostItemRepository, claimRepository);
    }

    @Test
    @Disabled("Test is disabled")
    void testUploadLostItemsFromFile_Failure() throws IOException {
        // Prepare mock data for the test
        List<LostItemEntity> parsedItems = Arrays.asList(new LostItemEntity(), new LostItemEntity());

        // Mock the file parsing behavior
        when(file.getOriginalFilename()).thenReturn("items.pdf");

        // Mock the getInputStream() method to return a ByteArrayInputStream (simulating file content)
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream("sample content".getBytes());
        when(file.getInputStream()).thenReturn(mockInputStream);

        // Mock the repository save behavior to simulate failure
        when(lostItemRepository.saveAll(anyList())).thenReturn(Arrays.asList(new LostItemEntity())); // Simulate mismatch

        // Assert exception on validation failure
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            lostItemService.uploadLostItemsFromFile(file, "pdf");
        });

        assertEquals("Not all items were saved successfully.", exception.getMessage());
    }
}