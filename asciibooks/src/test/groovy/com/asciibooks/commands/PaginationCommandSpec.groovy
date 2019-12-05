package com.asciibooks.commands

import spock.lang.Specification
import spock.lang.Unroll

class PaginationCommandSpec extends Specification {

    def paginationCommand

    def setup() {
        paginationCommand = new PaginationCommand()
    }

    def maxWrongValue = -1

    void "pagination defaults"() {
        expect: "A command with no inputs is valid"
            paginationCommand.validate()
        and:
            paginationCommand.max == 10
            paginationCommand.sort == null
            paginationCommand.order == null
            paginationCommand.offset == null
            paginationCommand.params == [max: 10, offset: null, sort: null, order: null]
            paginationCommand.getParams(foo: 'bar') == [max: 10, offset: null, sort: null, order: null, foo: 'bar']
    }

    void "pagination max limit to 100"() {
        when: "Max is over 100"
            paginationCommand.max = 1000

        then: "The command is valid"
            paginationCommand.validate()
        and: "Max is set to 100"
            paginationCommand.max == 100
    }

    void "paginagion not accept negatives"() {
        when: "Max is negative"
            paginationCommand.max = maxWrongValue
        then: "The command is invalid"
            !paginationCommand.validate()
        and: "Errors are returned"
            paginationCommand.errors.fieldError.field == 'max'
            paginationCommand.errors.fieldError.rejectedValue == maxWrongValue
    }

    void "offset can't be negative"() {
        when: "offset is negative"
            paginationCommand.offset = maxWrongValue
        then: "The command is invalid"
            !paginationCommand.validate()
        and: "error is present"
            paginationCommand.errors.fieldError.field == 'offset'
            paginationCommand.errors.fieldError.rejectedValue == maxWrongValue

    }

    @Unroll
    void "pagination order #order"() {
        when:
            paginationCommand.order = order
        then:
            paginationCommand.validate(['order']) == isValid
        and:
            paginationCommand.sort == 'id'

        where:
            order   ||  isValid
            'asc'   ||  true
            'foo'   ||  false
            null    ||  true
            'desc'  ||  true


    }
}