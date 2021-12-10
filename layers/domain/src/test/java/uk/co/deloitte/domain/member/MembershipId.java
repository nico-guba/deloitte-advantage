package uk.co.deloitte.domain.member;

import uk.co.deloitte.domain.ddd.Identity;

public class MembershipId extends Identity<String> {

    private MembershipId(final String value) {
        super(value);
    }

    public static MembershipId with(String value) {
        return new MembershipId(value);
    }
}
