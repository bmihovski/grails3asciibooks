package com.asciibooks

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class BookSpec extends Specification implements DomainUnitTest<Book> {

    def setup() {
    }

    def cleanup() {
    }

    void "Books are not published by default"() {
        expect:
            !domain.published
    }

    @Unroll
    void "Reject invalid prices #price"() {
        when: "An invalid price is given"
            domain.price = price

        then: "Price field does not validate"
            !domain.validate(['price'])
        where:
            price << [-1, 1_000_01]
    }

    void "valid price #price"() {
        when: "A valid price is given"
            domain.price = 9_99

        then: "Price field is valid"
            domain.validate(['price'])
    }

    @Unroll
    void "Content can store #length chars"() {
        when: "Given a long string"
            domain.content = ("a" * lenght)
        then: "Content is valid"
            domain.validate(['content'])
        where:
            lenght << [256, 100_000]
    }

    @Unroll
    void "Test Book.getFormattedPrice() Cents: #price Formatted: #formatted"() {
        given:
            domain.price = price

        expect:
            domain.formattedPrice == formatted

        where:
            price   || formatted
            1       || '$0.01'
            100     || '$1.00'
            110     || '$1.10'
            111     || '$1.11'
            1_000_00|| '$1,000.00'
    }
}
