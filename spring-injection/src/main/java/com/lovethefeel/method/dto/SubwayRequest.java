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
public class SubwayRequest {
    private String name;

    public static SubwayRequest from(final String name) {
        return new SubwayRequest(name);
    }

    public Subway toEntity() {
        return Subway.from(name);
    }
}
