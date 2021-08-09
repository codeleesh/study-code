package com.lovethefeel.springboot.dto;

import lombok.Getter;

@Getter
public class BotProfile {
    private String id;
    private boolean deleted;
    private String name;
    private String updated;
    private String app_id;
    private Icons icons;
    private String team_id;
}
