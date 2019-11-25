package com.asciibooks

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class UserPasswordSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "password too short"() {
        when:
            domain.password = "short"
        then:
            !domain.validate(['password'])
            domain.errors['password'].code == "minSize.notmet"
    }

    @Unroll
    void "password is too common: #password"() {
        when:
            domain.password = password

        then:
            !domain.validate(['password'])
            domain.errors['password'].code == "tooCommon"

        where:
            password    | _
            "12345678"  | _
            "password"  | _
            "123456789" | _
            "football"  | _
            "baseball"  | _
            "1234567890"| _
            "1qaz2wsx"  | _
            "princess"  | _
            "qwertyuiop"| _
            "starwars"  | _
    }

    @Unroll
    void "password is valid: #password"() {
        when:
            domain.password = password

        then:
            domain.validate(['password'])

        where:
            password        |   _
            "foobarbaz"     | _
            "a@93!fjdsa"    | _
            "10293845"      | _
            "StrongPass!24" | _
            "a" * 100       | _ // Very long passwords
            "a" * 1000      | _
    }

    void "invalid email"() {
        when:
            domain.email = "InvalidEmail"

        then:
            !domain.validate(['email'])
            domain.errors['email'].code == "email.invalid"
    }

    void "valid email"() {
        when:
            domain.email = "foo@bar.com"
        then:
            domain.validate(['email'])
    }

}