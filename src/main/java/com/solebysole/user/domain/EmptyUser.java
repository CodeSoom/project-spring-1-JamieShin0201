package com.solebysole.user.domain;

/**
 * 비어있는 회원.
 */
public class EmptyUser extends User {

    public EmptyUser() {
        super(null, null, null, null, null,
                null, null, false);
    }

}
