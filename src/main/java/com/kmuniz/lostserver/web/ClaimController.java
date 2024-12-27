package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.Claim;
import com.kmuniz.lostserver.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    // Get claim by ID
    @GetMapping("/claims/{id}")
    public String getClaimById(@PathVariable Long id, Model model) {
        Claim claim = claimService.getClaimById(id);
        model.addAttribute("claim", claim);
        return "claim-details";
    }

    // Endpoint to process a claim
    @PostMapping("/claims/{id}/claim")
    public String claimItem(@PathVariable Long id) {
        claimService.claimItem(id);
        return "redirect:/items";
    }
}