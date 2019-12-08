package com.asciibooks

class BookController {

    def show(Book book) {
        respond book
    }

    def index() {
        respond Book.list()
    }
}
