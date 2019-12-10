package com.asciibooks

import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_AUTHOR")
class DashboardController {

    def bookService
    def springSecurityService

    def index() {
        Long userId = springSecurityService.currentUserId as Long
        def books = bookService.getBooksAuthorUser(userId)
        respond(books: books)
    }

    def edit() {
        Long userId = springSecurityService.currentUserId as Long
        Long bookId = params.long('id')
        def book = bookService.getBookByAuthor(userId, bookId)
        respond(book: book)
    }

    def update() { }
    def delete() { }
    def create() { }
}
