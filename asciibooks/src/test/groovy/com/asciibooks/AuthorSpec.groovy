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

    void "Happy path"() {
        given:
            domain.name = "Eric"
            domain.biography = "Author"

        expect: "All constraints are satisfied"
            domain.validate()
    }

    void "Missing name"() {
        expect: "name is required"
            !domain.validate(['name'])
    }

    @Unroll
    void "Constrains: #name, #biography, #privateProfile isValid: #isValid"() {
        given:
            def address = new Address()
        when:
            domain.name = name
            domain.biography = biography
            domain.privateProfile = privateProfile
            domain.address = address
        then:
            domain.validate() == isValid

        where:
            name    |   biography   |   privateProfile  ||  isValid
            null    |   null        |   false           ||  false
            "Eric"  |   "Author"    |   true            ||  true
            "Eric"  |   "Author"    |   false           ||  true
            "Eric"  |   null        |   true            ||  true
            null    |   "Author"    |   null            ||  false

    }
}
