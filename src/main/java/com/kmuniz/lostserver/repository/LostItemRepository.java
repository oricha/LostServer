package com.kmuniz.lostserver.repository;

import com.kmuniz.lostserver.data.LostItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostItemRepository extends JpaRepository<LostItemEntity, Long> {

    List<LostItemEntity> findAll();
}
