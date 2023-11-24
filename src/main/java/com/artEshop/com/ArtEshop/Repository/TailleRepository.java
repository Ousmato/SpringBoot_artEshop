package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Taille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TailleRepository extends JpaRepository<Taille, Integer> {
    Taille findByLibelle(String libelle);
    Taille findByIdTaille(int idTaille);
}
