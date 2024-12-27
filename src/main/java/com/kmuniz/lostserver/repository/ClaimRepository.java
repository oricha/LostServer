package com.kmuniz.lostserver.repository;

import com.kmuniz.lostserver.data.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByUserId(Long userId);

    @Query("SELECT c.lostItemId, c.quantityClaimed, l.itemName, l.place FROM Claim c JOIN LostItemEntity l ON c.lostItemId = l.id")
    List<Object[]> findClaimsWithUserDetails();
}