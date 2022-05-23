package com.company.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);

    void deleteByPhone(String phone);

    @Transactional
    @Modifying
    @Query("update User u " +
            "set u.enabled = true where u.phone=?1")
    void enableUser(String phone);

    @Transactional
    @Modifying
    @Query("update User set firstName=:firstName, lastName=:lastName where phone=:phone")
    void updateUserDetail(@Param("firstName") String firstName,
                          @Param("lastName") String lastName,
                          @Param("phone") String phone);
}