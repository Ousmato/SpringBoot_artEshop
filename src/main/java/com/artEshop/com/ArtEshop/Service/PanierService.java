package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Commandes;
import com.artEshop.com.ArtEshop.Entity.Notification;
import com.artEshop.com.ArtEshop.Repository.CommandeRepository;
import com.artEshop.com.ArtEshop.Repository.PanierRepository;
import com.artEshop.com.ArtEshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommandeRepository commandeRepository;


}
