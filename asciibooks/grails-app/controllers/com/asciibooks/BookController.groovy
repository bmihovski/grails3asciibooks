package com.asciibooks

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional(readOnly = true)
class BookController {

    static namespace = "v1"
    static responseFormats = ['json']
    static allowedMethods = [
            save: "POST",
            update: ["PUT", "POST"],
            patch: "PATCH",
            delete: "DELETE",
            text: "GET"
    ]

    @Secured("ROLE_ADMIN")
    def show(Book book) {
        respond book
    }

    @Secured("ROLE_ADMIN")
    def index() {
        respond Book.list()
    }

    @Secured("ROLE_ADMIN")
    def text(Book book) {
        if (book) {
            respond id: book?.id, "text": book?.text
        } else {
            render status: NOT_FOUND
        }
    }
}
