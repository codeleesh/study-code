package me.lovethefeel.jpahistory.write.dto;

import lombok.Getter;
import me.lovethefeel.jpahistory.write.domain.Write;

@Getter
public class WriteResponse {

    private Long writeId;
    private String writeName;

    protected WriteResponse() {}

    private WriteResponse(final Long writeId, final String writeName) {

        this.writeId = writeId;
        this.writeName = writeName;
    }

    public static WriteResponse fromResponse(final Write write) {

        return new WriteResponse(write.getId(), write.getWriteName());
    }
}
