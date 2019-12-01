package com.asciibooks.commands

import spock.lang.Specification

class PaginationCommandSpec extends Specification {

    def paginationCommand

    def setup() {
        paginationCommand = new PaginationCommand()
    }

    void "pagination defaults"() {
        expect: "A command with no inputs is valid"
            paginationCommand.validate()
        and:
            paginationCommand.max == 10
            paginationCommand.sort == null
            paginationCommand.order == null
            paginationCommand.offset == null
            paginationCommand.params == [max: 10, offset: null, sort: null, order: null, foo: 'bar']
    }

    void "pagination max limit to 100"() {
        when: "Max is over 100"
            paginationCommand.max == 1000

        then: "The command is valid"
            paginationCommand.validate()
        and: "Max is set to 100"
            paginationCommand.max == 100
    }

}