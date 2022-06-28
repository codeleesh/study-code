package me.lovethefeel.domain.article;

import jakarta.persistence.*;
import me.lovethefeel.domain.common.Address;

/**
 * @SecondaryTable + 테이블명
 * - 저장시
 *   - 해당 테이블 및 @SecondaryTable의 Insert 쿼리 생성
 *   - @Embeddable 객체가 null인 경우 테이블에 저장하지 않음
 * - 조회시
 *   - left join으로 조회
 * - 수정시
 *   - @SecondaryTable이 null아니면, 값이 존재하니깐 update 실행
 *   - @SecondaryTable이 null이면, 값이 존재하지 않으니깐 insert 실행
 *   - @SecondaryTable을 null로 수정하면, @SecondaryTable이 null이 아니면 delete 실행
 * - 삭제시
 *   -
 * - @Embeddable 다른 테이블에 매핑할때 사용
 * - @SecondaryTable.name과 객체.@Column.table은 동일하게 설정
 * - @SecondaryTable.pkJoinColumns은 외래키 지정
 *   - @PrimaryKeyJoinColumn.name : 외래키 필드 이름
 *   - @PrimaryKeyJOinColumn.referencedColumnName : 외래키의 원본 테이블 필드
 *
 * @SecondaryTable + @AttributeOverride
 * - @Embeddable 다른 테이블에 매핑할때 사용
 * - 단, @Embeddable 객체가 공통으로 사용되는 경우에 한해서 사용
 * - @Column의 table 옵션을 이용해서 설정
 */

@Entity
@Table(name = "writer")
@SecondaryTables({
        @SecondaryTable(name = "writer_address",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "writer_id", referencedColumnName = "id")
        ),
        @SecondaryTable(name = "writer_intro",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "writer_id", referencedColumnName = "id")
        )}
)
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(table = "writer_address", name = "addr1")),
            @AttributeOverride(name = "address2", column = @Column(table = "writer_address", name = "addr2")),
            @AttributeOverride(name = "zipcode", column = @Column(table = "writer_address"))
    })
    private Address address;

    @Embedded
    private Intro intro;

    protected Writer() {
    }

    public Writer(String name, Address address, Intro intro) {
        this.name = name;
        this.address = address;
        this.intro = intro;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Intro getIntro() {
        return intro;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", intro=" + intro +
                '}';
    }
}
