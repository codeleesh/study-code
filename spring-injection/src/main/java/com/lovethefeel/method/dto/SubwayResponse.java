package com.lovethefeel.method.dto;

import com.lovethefeel.method.domain.Subway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class SubwayResponse {
    private Long id;
    private String name;

    public static SubwayResponse of(final Long id, final String name) {
        return new SubwayResponse(id, name);
    }

    public static SubwayResponse from(final Subway subway) {
        return new SubwayResponse(subway.getId(), subway.getName());
    }
}
