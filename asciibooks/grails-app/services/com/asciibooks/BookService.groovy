package com.asciibooks

import grails.databinding.DataBindingSource
import grails.gorm.PagedResultList
import grails.gorm.transactions.Transactional
import grails.web.databinding.DataBinder

@Transactional
class BookService implements DataBinder {

    Book getBook(Long id) {
        Book.get(id)
    }

    Book getBookByAuthor(Long userId, Long bookId) {
        Book.createCriteria().get() {
            author {
                user {
                    eq('id', userId)
                }
            }
            eq('id', bookId)
        } as Book
    }

    PagedResultList getBooksAuthor(Long authorId, Map params = [:]) {
        Book.createCriteria().list(params) {
            author {
                eq('id', authorId)
            }
        } as PagedResultList
    }

    PagedResultList getBooksAuthorUser(Long userId, Map params = [:]) {
        Book.createCriteria().list(params) {
            author {
                user {
                    eq('id', userId)
                }
            }
        } as PagedResultList
    }

    @Transactional
    Book save(DataBindingSource props, boolean flush = false) {
        def book = new Book()
        bindData(book, props)
        book.save(flush: flush)
        book
    }

    @Transactional
    Book update(Book book, DataBindingSource props, boolean flush = false) {
        bindData(book, props)
        book.save(flush: flush)
        book
    }

    @Transactional
    void delete(Long bookId, boolean flush = false) {
        def book = Book.load(bookId)
        book.delete()
    }

    @Transactional
    void delete(Book book, boolean flush = false) {
        delete(book.id, flush)
    }
}
