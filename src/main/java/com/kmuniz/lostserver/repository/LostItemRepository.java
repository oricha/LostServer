package com.kmuniz.lostserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LostItemRepository extends JpaRepository<LostItemEntity, Long> {
}
