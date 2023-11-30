package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    Notification findByIdNotification(int idNotification);
    List<Notification> findAllByProduitsPublier(boolean isPublic);
    void deleteAllByProduitsIdProduit(int idProduit);
}

