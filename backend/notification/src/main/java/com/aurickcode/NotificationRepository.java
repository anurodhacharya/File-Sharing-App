package com.aurickcode;

import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {

    private NotificationJPARepository notificationJPARepository;

    public NotificationRepository(NotificationJPARepository notificationJPARepository) {
        this.notificationJPARepository = notificationJPARepository;
    }

    public void saveMessage(Notification notification) {
        notificationJPARepository.save(notification);
    }
}
