package me.lovethefeel.dto;

import me.lovethefeel.domain.Write;

import java.time.LocalDateTime;

public class WriteResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private String createdBy;

    private WriteResponse(final Long id, final String title, final String content, final LocalDateTime created
            , final String createdBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = created;
        this.createdBy = createdBy;
    }

    public static WriteResponse from(final Write write) {
        return new WriteResponse(write.getId(), write.getTitle(), write.getContent(), write.getCreated(), write.getCreatedBy());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
