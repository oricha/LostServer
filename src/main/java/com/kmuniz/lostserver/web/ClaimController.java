package com.kmuniz.lostserver.web;

import com.kmuniz.lostserver.data.Claim;
import com.kmuniz.lostserver.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService claimService;

    // Constructor-based dependency injection
    @Autowired
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    /**
     * Fetches a specific claim by its ID.
     *
     * @param id    the ID of the claim
     * @param model the model to pass data to the view
     * @return the name of the HTML template to display claim details
     */
    @GetMapping("/{id}")
    public String getClaimById(@PathVariable Long id, Model model) {
        try {
            Claim claim = claimService.getClaimById(id);
            model.addAttribute("claim", claim);
            return "claim-details";
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching claim: " + e.getMessage());
            model.addAttribute("error", "Could not find the claim.");
            return "error"; // Redirect to a generic error page or show an error message
        }
    }

    /**
     * Processes a claim for a lost item.
     *
     * @param userId         the ID of the user making the claim
     * @param lostItemId     the ID of the lost item being claimed
     * @param quantityClaimed the quantity of the lost item being claimed
     * @param model          the model to pass data to the view
     * @return a redirect to the items page
     */
    @PostMapping("/{lostItemId}/claim")
    public String claimLostItem(
            @RequestParam Long userId,
            @PathVariable Long lostItemId,
            @RequestParam int quantityClaimed,
            Model model) {
        try {
            claimService.claimItem(userId, lostItemId, quantityClaimed);
            model.addAttribute("message", "Item claimed successfully!");
        } catch (IllegalArgumentException e) {
            // Log the exception
            System.err.println("Error processing claim: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("Unexpected error: " + e.getMessage());
            model.addAttribute("error", "An unexpected error occurred while processing your claim.");
        }
        return "redirect:/items"; // Redirect to items page
    }

    /**
     * Retrieves all claims.
     *
     * @param model the model to pass data to the view
     * @return the name of the HTML template to display all claims
     */
    @GetMapping
    public String getAllClaims(Model model) {
        try {
            model.addAttribute("claims", claimService.getAllClaims());
            return "claims-page";
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching claims: " + e.getMessage());
            model.addAttribute("error", "Could not fetch claims.");
            return "error"; // Redirect to an error page
        }
    }

    @GetMapping("/admin/claims")
    public String getClaimedLostItems(Model model) {
        model.addAttribute(claimService.getClaimedLostItems());
        return "claims-page";
    }
}