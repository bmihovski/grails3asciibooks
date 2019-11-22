package com.asciibooks

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class AuthorSpec extends Specification implements DomainUnitTest<Author> {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "DisplayName is correct"() {
        when:
            domain.name = name
            domain.penName = penName
        then:
            domain.displayName == displayName
        where:
            name   |  penName  || displayName
            "Eric" |  "Author" || "Author"
            "Eric" |  null     || "Eric"
    }

    void "is active by default"() {
        given:
            def author = new Author()
        expect:
            author.active

    }
}
