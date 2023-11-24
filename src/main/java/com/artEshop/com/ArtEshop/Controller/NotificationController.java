package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.Notification;
import com.artEshop.com.ArtEshop.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/read")
    public ResponseEntity<Notification> read(@PathVariable int idNotification){
        try {
            Notification  notification =  notificationService.read(idNotification);
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

//    :::::::::::::;list pour admin
    @GetMapping("/list")
    public  ResponseEntity<List<Notification>> readAll(boolean isPublic) {
        try {
            List<Notification> listNotifie = notificationService.reaAll(isPublic);
return new ResponseEntity<>(listNotifie,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
