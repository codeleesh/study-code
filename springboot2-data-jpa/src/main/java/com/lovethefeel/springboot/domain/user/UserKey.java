package com.lovethefeel.springboot.domain.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserKey implements Serializable {
    @Id
    private String userId;

    @Id
    private String userName;

}
