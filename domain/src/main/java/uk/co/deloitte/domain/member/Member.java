package uk.co.deloitte.domain.member;

import uk.co.deloitte.domain.ddd.Aggregate;

import java.util.Objects;

public class Member implements Aggregate<MemberId> {

    private final MemberId id;

    public boolean isActive() {
        return active;
    }

    private boolean active;

    public void deactivate() {
        active = false;
    }
    private Member(final MemberId id) {
        this.id = id;
    }

    public static Member with(MemberId id) {
        return new Member(id);
    }

    public void activate() {
        active = true;
    }

    @Override
    public MemberId id() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Member member = (Member) o;
        return id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
