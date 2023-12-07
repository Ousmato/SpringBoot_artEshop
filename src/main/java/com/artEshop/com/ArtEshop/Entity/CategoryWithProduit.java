package com.artEshop.com.ArtEshop.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryWithProduit {
    private Categories category;
    private int productCount;
}
