package com.kmuniz.lostserver.service;

import com.kmuniz.lostserver.data.LostItemEntity;
import com.kmuniz.lostserver.repository.LostItemRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LostItemServiceTest {
    @Autowired
    private LostItemService lostItemService;

    @Mock
    private LostItemRepository lostItemRepository;

    @Test
    @Disabled("This test is disabled for demonstration purposes.")
    public void testGetAllLostItems() {
        List<LostItemEntity> mockItems = List.of(new LostItemEntity(1L,"Laptop", 1, "Taxi"));
        Mockito.when(lostItemRepository.findAll()).thenReturn(mockItems);

        List<LostItemEntity> result = lostItemService.getAllLostItems();
        assertEquals(1, result.size());
    }
}