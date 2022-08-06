package item1.domain;


import java.time.LocalDateTime;

public class Write {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updateBy;

    private Write(final Long id, final String title, final String content, final LocalDateTime created
            , final String createdBy, final LocalDateTime updated, final String updateBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updateBy = updateBy;
    }

    public static Write initCreate(final Long id, final String title, final String content, final String createdBy) {
        final LocalDateTime now = LocalDateTime.now();
        return new Write(id, title, content, now, createdBy, now, createdBy);
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

    public LocalDateTime getUpdated() {
        return updated;
    }

    public String getUpdateBy() {
        return updateBy;
    }
}
