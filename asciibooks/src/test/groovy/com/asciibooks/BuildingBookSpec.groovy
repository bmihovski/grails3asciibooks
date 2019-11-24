package com.asciibooks

import grails.buildtestdata.UnitTestDataBuilder
import spock.lang.Specification

class BuildingBookSpec extends Specification implements UnitTestDataBuilder {

    def setupSpec() {
        mockDomains(Book, Author)
    }

    void "Author book Relationship"() {
        given: "An author with one book"
            def author = build(Author)
            build(Book, [author: author])

        and: "An another unrelated book"
            build(Book, [author: build(Author)])
            build(Book, [author: build(Author)])

        expect: "author.books only return one book owned by this author."
            author.books.size() == 1
        and: "and we have 3 books in database"
            Book.count() == 3

    }
}