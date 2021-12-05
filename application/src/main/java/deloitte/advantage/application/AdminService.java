package deloitte.advantage.application;

import uk.co.deloitte.domain.member.IMemberRepository;
import uk.co.deloitte.domain.member.Member;
import uk.co.deloitte.domain.member.MemberId;

public class AdminService {

    private final IMemberRepository members;

    private AdminService(final IMemberRepository members) {
        this.members = members;
    }

    public static AdminService create(IMemberRepository members) {
        return new AdminService(members);
    }

    public void register(final Member member) {
        member.activate();
        members.create(member);
    }

    public void deactivate(final MemberId memberId) {
        Member member = members.read(memberId).orElseThrow();
        member.deactivate();
        members.update(member);
    }
}
