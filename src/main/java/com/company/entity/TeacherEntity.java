package com.company.entity;

import com.company.enums.UserRole;
import com.company.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.usertype.UserType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity extends BaseEntity {
    @Column
    private Double salary = 0.0;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}