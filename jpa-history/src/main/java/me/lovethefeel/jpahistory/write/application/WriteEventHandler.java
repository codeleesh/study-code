package me.lovethefeel.jpahistory.write.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.jpahistory.write.domain.Write;
import me.lovethefeel.jpahistory.write.domain.WriteHistory;
import me.lovethefeel.jpahistory.write.repository.WriteHistoryRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class WriteEventHandler {

    private final WriteHistoryRepository historyRepository;

    @EventListener
    public void writeHistory(final WriteEvent.WriteHistory event) {
        this.saveWriteHistory(event.getWrite(), event.getComment());
    }

    private void saveWriteHistory(final Write write, final String comment) {

        if (Objects.isNull(write)) {
            return ;
        }

        log.info("saveWriteHistory write {} comment {}", write, comment);
        final WriteHistory writeHistory = WriteHistory.fromEntity(write, comment);
        historyRepository.save(writeHistory);
    }
}
