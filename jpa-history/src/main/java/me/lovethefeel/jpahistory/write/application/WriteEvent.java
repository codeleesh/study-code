package me.lovethefeel.jpahistory.write.application;

import me.lovethefeel.jpahistory.write.domain.Write;

public class WriteEvent {

    protected WriteEvent() {}

    public static class WriteHistory {
        private Write write;
        private String comment;
        private WriteHistory(final Write write, final String comment) {

            this.write = write;
            this.comment = comment;
        }

        public static WriteHistory of(final Write write, final String comment) {
            return new WriteHistory(write, comment);
        }

        public Write getWrite() {
            return this.write;
        }

        public String getComment() {
            return this.comment;
        }
    }
}
