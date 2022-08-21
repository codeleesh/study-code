package me.lovethefeel.jpahistory.write.application;

import me.lovethefeel.jpahistory.write.domain.Write;
import me.lovethefeel.jpahistory.write.domain.WriteHistory;
import me.lovethefeel.jpahistory.write.dto.WriteRequest;
import me.lovethefeel.jpahistory.write.dto.WriteResponse;
import me.lovethefeel.jpahistory.write.repository.WriteHistoryRepository;
import me.lovethefeel.jpahistory.write.repository.WriteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = MOCK)
class WriteServiceTest {

    @Autowired
    private WriteService writeService;

    @Autowired
    private WriteRepository writeRepository;

    @Autowired
    private WriteHistoryRepository historyRepository;

    @Test
    @Transactional
    void writeSave() {

        final Timestamp currentTimestamp = Timestamp.valueOf("2022-08-12 22:10:00.000");
        final WriteRequest 작가_요청 = WriteRequest.from("lsh");
        final Write 작가_생성_저장값 = Write.ofCreate(1L, "lsh", currentTimestamp, "lsh", currentTimestamp, "lsh");

        final WriteResponse 작가_응답 = writeService.createWrite(작가_요청);

        assertAll(
                () -> assertThat(작가_응답).isNotNull(),
                () -> assertThat(작가_응답.getWriteName()).isEqualTo("lsh")
        );

        final Write findWrite = writeRepository.findById(작가_응답.getWriteId())
                .orElseThrow(IllegalArgumentException::new);

        assertAll(
                () -> assertThat(findWrite).isNotNull(),
                () -> assertThat(findWrite.getWriteName()).isEqualTo("lsh")
        );

        final List<WriteHistory> findWriteHistory = historyRepository.findByWriteId(findWrite.getId());

        assertAll(
                () -> assertThat(findWriteHistory).isNotNull()
        );
    }
}