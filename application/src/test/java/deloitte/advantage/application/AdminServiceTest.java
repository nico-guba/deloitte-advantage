package deloitte.advantage.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.member.IMemberRepository;
import uk.co.deloitte.domain.member.Member;
import uk.co.deloitte.domain.member.MemberId;

import static org.assertj.core.api.Assertions.assertThat;

class AdminServiceTest {

    IMemberRepository members = MemberRepositoryInMemory.empty();

    AdminService service;

    MemberId memberId = MemberId.unique();

    private Member registerMember(final MemberId memberId) {
        assertThat(members.read(memberId)).isEmpty();

        Member member = Member.with(memberId);
        service.register(member);
        return member;
    }

    @BeforeEach
    void beforeEach()
    {
        service = AdminService.create(members);
    }

    @Test
    void registerMember_persistsObject() {
        Member member = registerMember(memberId);

        assertThat(members.read(memberId)).isNotEmpty().get().isEqualTo(member);
    }

    @Test
    void registerMember_setsActiveAttribute() {
        Member member = registerMember(memberId);

        Member actual = members.read(memberId).orElseThrow();

        assertThat(actual.isActive()).isTrue();
    }

    @Test
    void deactivateMember() {
        Member member = registerMember(memberId);

        service.deactivate(memberId);

        Member actual = members.read(memberId).orElseThrow();
        assertThat(actual.isActive()).isFalse();
    }
}