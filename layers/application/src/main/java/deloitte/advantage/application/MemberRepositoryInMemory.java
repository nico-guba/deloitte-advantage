package deloitte.advantage.application;

import uk.co.deloitte.domain.member.IMemberRepository;
import uk.co.deloitte.domain.member.Member;
import uk.co.deloitte.domain.member.MemberId;

public class MemberRepositoryInMemory extends InMemoryRepository<MemberId, Member> implements IMemberRepository {

    public static IMemberRepository empty() {
        return new MemberRepositoryInMemory();
    }
}
