package me.lovethefeel.jpahistory.write.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.jpahistory.write.domain.Write;
import me.lovethefeel.jpahistory.write.domain.WriteHistory;
import me.lovethefeel.jpahistory.write.dto.WriteRequest;
import me.lovethefeel.jpahistory.write.dto.WriteResponse;
import me.lovethefeel.jpahistory.write.repository.WriteHistoryRepository;
import me.lovethefeel.jpahistory.write.repository.WriteRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WriteService {

    private final WriteRepository writeRepository;
    private final WriteHistoryRepository historyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public WriteResponse createWrite(final WriteRequest writeRequest) {

        final Write saveWrite = writeRepository.save(writeRequest.toCreateEntity());
        applicationEventPublisher.publishEvent(WriteEvent.WriteHistory.of(saveWrite, "신규 저장"));

        return WriteResponse.fromResponse(saveWrite);
    }

    @Transactional
    public WriteResponse updateWrite(final WriteRequest writeRequest) {

        final Write findWrite = writeRepository.findById(writeRequest.getWriteId())
                .orElseThrow(IllegalArgumentException::new);

        findWrite.updateName(writeRequest.getWriteName());
        applicationEventPublisher.publishEvent(WriteEvent.WriteHistory.of(findWrite, "이름 변경"));

        return WriteResponse.fromResponse(findWrite);
    }

    @Transactional
    public void writeHistory(final Write write, final String comment) {

        if (Objects.isNull(write)) {
            log.warn("Write is null");
            return ;
        }

        final WriteHistory writeHistory = WriteHistory.fromEntity(write, comment);
        historyRepository.save(writeHistory);
    }
}
