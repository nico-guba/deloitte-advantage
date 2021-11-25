package uk.co.deloitte.advantage.zone.presentation;

import java.util.StringJoiner;
import java.util.UUID;

public class ZoneIdMessage {
    public UUID id;

    public ZoneIdMessage() {

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", ZoneIdMessage.class.getSimpleName()), "]")
                .add(String.format("id='%s'", id))
                .toString();
    }
}
