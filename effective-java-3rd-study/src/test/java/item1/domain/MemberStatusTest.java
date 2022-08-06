package item1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberStatusTest {

    @Test
    void 사용자_상태_조회() {
        final MemberStatus memberStatus = MemberStatus.valueOf("INPROGRESS");

        assertEquals(memberStatus, MemberStatus.INPROGRESS);
    }
}