package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.RequestNotification;

import java.util.List;

@Repository
public interface RequestNotificationRepository extends JpaRepository<RequestNotification,Long> {
    @Query("select rn from RequestNotification rn join rn.accounts a where a.accountId=?1 order by rn.id desc")
    List<RequestNotification> getRequestNotificationByAccountId(String id);
}
