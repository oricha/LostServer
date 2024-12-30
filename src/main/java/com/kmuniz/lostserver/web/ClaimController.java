package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.Claim;
import com.kmuniz.lostserver.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping("/claims")
    public String getClaims(Model model) {
        model.addAttribute("claims", claimService.getAllClaims());
        return "claims-page";
    }

    // Get claim by ID
    @GetMapping("/claims/{id}")
    public String getClaimById(@PathVariable Long id, Model model) {
        Claim claim = claimService.getClaimById(id);
        model.addAttribute("claim", claim);
        return "claim-details";
    }

    // Endpoint to process a claim
    @PostMapping("/claims/{lostItemId}/claim")
    public String claimLostItem(
            @RequestParam Long userId,
            @PathVariable Long lostItemId, // Use @PathVariable for URL-based ID
            @RequestParam int quantityClaimed,
            Model model) {

        try {
            claimService.claimItem(userId, lostItemId, quantityClaimed);
            model.addAttribute("message", "Item claimed successfully!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/items";
    }
}