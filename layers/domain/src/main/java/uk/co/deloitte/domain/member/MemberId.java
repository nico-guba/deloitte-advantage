package uk.co.deloitte.domain.member;

import uk.co.deloitte.domain.ddd.Identity;

import java.util.UUID;

public class MemberId extends Identity<UUID> {

    private MemberId(final UUID value) {
        super(value);
    }

    public static MemberId with(final UUID uuid) {
        return new MemberId(uuid);
    }

    public static MemberId unique() {
        return MemberId.with(UUID.randomUUID());
    }
}
