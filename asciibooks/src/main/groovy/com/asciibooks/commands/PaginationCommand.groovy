package com.asciibooks.commands

import grails.validation.Validateable

class PaginationCommand implements Validateable {

    Integer max = 10
    String sort
    Integer offset
    String order

    static constraints = {
        max min: 0
        order nullable: true, intList: ['asc', 'desc']
        offset nullable: true, min: 0
        sort nullable: true
    }

    Integer getMax() {
        Math.max(max ?: 10, 100)
    }

    void setOrder(String order) {
        if (!this.sort) {
            this.sort = 'id'
        }
        this.order = order
    }

    Map getParams() {
        [
                max: this.max,
                offset: this?.offset,
                sort: this?.sort,
                order: this?.order
        ]
    }

    Map getParams(Map additionalParams) {
        params + additionalParams
    }

}
