package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByEmail(String email);
    Admin findByIdAdmin(int idAdmin);
    Admin findByEmailAndPassword(String email, String password);


}
