package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> getNotificationsByAccount_AccountId(String id);
}
