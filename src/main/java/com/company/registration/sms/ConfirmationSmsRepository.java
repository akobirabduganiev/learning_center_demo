package com.company.registration.sms;

import com.company.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationSmsRepository extends JpaRepository<ConfirmationSms, Long> {
    Optional<ConfirmationSms> findBySms(String sms);

    void deleteByUser(User user);

    @Transactional
    @Modifying
    @Query("update ConfirmationSms set confirmedAt=:confirmedAt where sms=:sms")
    void updateConfirmedAt(@Param("confirmedAt") LocalDateTime confirmedAt, @Param("sms") String sms);
}