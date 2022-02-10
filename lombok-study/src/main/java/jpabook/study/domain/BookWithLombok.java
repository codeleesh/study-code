package jpabook.study.domain;

import lombok.*;

@AllArgsConstructor(onConstructor_={@Builder})
public class BookWithLombok {

    private final Long id;
    private final String isbn;
    private String name;
    private String author;
    private final boolean useYn;
}
