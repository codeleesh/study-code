package com.lovethefeel.queue.domain;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 롤러코스터 대기열 관리 객체
 */
public class RollerCoaster {
    /**
     * 대기열 필드
     */
    private Queue<Person> persons;

    /**
     * 기본 생성자
     * @param persons
     */
    public RollerCoaster(final Queue<Person> persons) {
        this.persons = persons;
    }

    /**
     * 초기화
     * @return
     */
    public static RollerCoaster init() {
        return new RollerCoaster(new LinkedList<>());
    }

    /**
     * Queue 생성 메소드 - offer
     *
     * @param person
     * @return
     */
    public boolean lineUpOffer(final Person person) {
        return this.persons.offer(person);
    }

    /**
     * Queue 생성 메소드 - add
     * @param person
     * @return
     */
    public boolean lineUpAdd(final Person person) {
        return this.persons.add(person);
    }

    /**
     * Queue front 조회 메소드 - peek
     * @return
     */
    public Person searchFrontPeek() {
        return this.persons.peek();
    }

    /**
     * Queue front 조회 메소드 - element
     * @return
     */
    public Person searchFrontElement() {
        return this.persons.element();
    }

    /**
     * Queue 삭제 메소드 - poll
     * @return
     */
    public Person lineOutPoll() {
        return this.persons.poll();
    }

    /**
     * Queue 삭제 메소드 - remove
     * @return
     */
    public Person lineOutRemove() {
        return this.persons.remove();
    }
}
