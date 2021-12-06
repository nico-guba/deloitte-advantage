package uk.co.deloitte.domain.member;

import uk.co.deloitte.domain.ddd.support.IdentityVerifier;

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