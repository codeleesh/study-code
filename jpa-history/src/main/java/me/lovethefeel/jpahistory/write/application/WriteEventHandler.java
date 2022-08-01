package me.lovethefeel.jpahistory.write.application;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static me.lovethefeel.jpahistory.write.application.WriteEvent.*;

@Component
@RequiredArgsConstructor
public class WriteEventHandler {

    private final WriteService writeService;

    @EventListener
    public void writeHistory(final WriteHistory event) {
        writeService.writeHistory(event.getWrite(), event.getComment());
    }
}
