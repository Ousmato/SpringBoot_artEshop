package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByIdUser(int idUser);
    User findByEmailAndPassword(String email, String password);


}
