package com.kmuniz.lostserver.web.requestResponse;

import lombok.Data;

@Data
public class ClaimRequest {

    private Long userId;
    private Long lostItemId;
    private int quantityClaimed = 1;
}
