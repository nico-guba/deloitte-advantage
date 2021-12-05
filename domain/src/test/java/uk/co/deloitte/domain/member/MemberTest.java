package uk.co.deloitte.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberTest {
    Member member = Member.with(MemberId.unique());

    @Test
    void activate() {
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();
    }

    @Test
    void deactivate() {
        member.deactivate();

        assertThat(member.isActive()).isFalse();
    }

    @Test
    void password_matches() {
        member.password(Password.with("abcdefg"));

        assertThat(member.hasPassword("abcdefg")).isTrue();
    }

    @Test
    void password_mismatch() {
        member.password(Password.with("abcdefg"));

        assertThat(member.hasPassword("aabbccddeeffgg")).isFalse();
    }

}