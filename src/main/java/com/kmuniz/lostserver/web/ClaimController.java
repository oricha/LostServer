package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.Claim;
import com.kmuniz.lostserver.service.ClaimService;
import com.kmuniz.lostserver.web.requestResponse.ClaimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

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
    @PostMapping("/claims/{lostItemId}/claim")
    public String claimItem(@PathVariable Long lostItemId, @RequestBody ClaimRequest claimRequest, Model model) {

        try {
            claimService.claimItem(claimRequest.getUserId(), lostItemId, claimRequest.getQuantityClaimed());
            model.addAttribute("message", "Item claimed successfully!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/items";
    }
    @GetMapping("/claims")
    public String getClaims(Model model) {
        model.addAttribute("claims", claimService.getAllClaims());
        return "claims-page";
    }
}