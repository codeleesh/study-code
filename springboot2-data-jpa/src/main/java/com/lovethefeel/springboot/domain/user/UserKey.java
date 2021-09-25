package com.lovethefeel.springboot.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

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
