package com.kmuniz.lostserver.repository;

import com.kmuniz.lostserver.data.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    @Query("SELECT c, li FROM Claim c JOIN LostItemEntity li ON c.lostItemId = li.id")
    List<Object[]> findClaimsWithUserDetails();
}