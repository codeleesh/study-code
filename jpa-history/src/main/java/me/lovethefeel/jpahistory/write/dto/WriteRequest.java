package me.lovethefeel.jpahistory.write.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.lovethefeel.jpahistory.write.domain.Write;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@JsonInclude(NON_NULL)
public class WriteRequest {

    private Long writeId;
    private String writeName;

    protected WriteRequest() {}

    private WriteRequest(final String writeName) {
        this.writeName = writeName;
    }

    private WriteRequest(final Long writeId, final String writeName) {
        this.writeId = writeId;
        this.writeName = writeName;
    }

    public static WriteRequest from(final String writeName) {
        return new WriteRequest(writeName);
    }

    public static WriteRequest of(final Long writeId, final String writeName) {
        return new WriteRequest(writeId, writeName);
    }

    public Write toCreateEntity() {
        return Write.fromCreate(this.writeName);
    }
}
