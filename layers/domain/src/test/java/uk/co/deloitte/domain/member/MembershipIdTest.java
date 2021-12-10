package uk.co.deloitte.domain.member;

import uk.co.deloitte.domain.ddd.support.IdentityVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MembershipIdTest implements IdentityVerifier<MembershipId> {

    @Override
    public MembershipId invalidInstance() {
        return MembershipId.with(null);
    }

    @Override
    public MembershipId validInstance() {
        return MembershipId.with("full");
    }
}