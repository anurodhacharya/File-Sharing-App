package com.aurickcode;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = "notification")
    public void receiveMessage(String message) {
        Notification notification = new Notification(message);
        notificationRepository.saveMessage(notification);
    }
}
