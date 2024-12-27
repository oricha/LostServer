package com.kmuniz.lostserver.data;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "claim")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long lostItemId;
    private int quantityClaimed;
}
