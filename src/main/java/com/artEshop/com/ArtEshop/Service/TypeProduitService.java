package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Produit_Color;
import com.artEshop.com.ArtEshop.Entity.Types;
import com.artEshop.com.ArtEshop.Repository.Produit_ColorRepository;
import com.artEshop.com.ArtEshop.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProduitService {
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private Produit_ColorRepository produit_colorRepository;

    public List<Types> readallProduitType(){
        return typeRepository.findAll();

    }


}
