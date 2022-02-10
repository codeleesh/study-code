package jpabook.study.domain;

public class BookWithOutLombok {

    private Long id;
    private String isbn;
    private String name;
    private String author;

    private BookWithOutLombok() {
    }

    public static BookWithOutLombok of() {
        return new BookWithOutLombok();
    }
}
