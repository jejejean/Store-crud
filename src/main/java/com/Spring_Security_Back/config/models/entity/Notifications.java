package com.Spring_Security_Back.config.models.entity;

import com.Spring_Security_Back.user.models.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Column(name = "service_number")
    private String serviceNumber;
    private boolean readed;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;


}
