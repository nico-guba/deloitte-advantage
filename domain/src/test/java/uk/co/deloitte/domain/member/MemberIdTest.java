package uk.co.deloitte.domain.member;

import uk.co.deloitte.domain.ddd.support.IdentityVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MemberIdTest implements IdentityVerifier<MemberId> {

    @Override
    public MemberId invalidInstance() {
        return MemberId.with(null);
    }

    @Override
    public MemberId validInstance() {
        return MemberId.unique();
    }
}