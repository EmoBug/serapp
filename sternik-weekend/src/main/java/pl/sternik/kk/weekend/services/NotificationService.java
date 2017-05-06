package pl.sternik.kk.weekend.services;

import java.util.List;

import pl.sternik.kk.weekend.services.NotificationServiceImpl.NotificationMessage;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
    List<NotificationMessage> getNotificationMessages();
}