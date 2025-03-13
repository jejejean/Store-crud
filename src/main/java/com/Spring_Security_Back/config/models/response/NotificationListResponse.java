package com.Spring_Security_Back.config.models.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationListResponse {
    private Long id;
    private String content;
    private String serviceNumber;
    private boolean readed;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    private String username;
    private Long idClient;
}
