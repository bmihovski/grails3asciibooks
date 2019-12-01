package com.asciibooks

import grails.gorm.PagedResultList
import grails.gorm.transactions.Transactional
import grails.web.databinding.DataBinder

@Transactional(readOnly = true)
class AuthorService implements DataBinder {

    Author getAuthor(Long id) {
        Author.get(id)
    }

    def getAuthors(Map params = null) {
        Author.list(params)
    }

    PagedResultList getAuthorsBooks(Long id, Map params = null) {
        Book.createCriteria().list(params) {
            author {
                eq('id', id)
            }
        } as PagedResultList<Book>
    }

    @Transactional
    def save(Map props, User user, boolean flush = false) {
        def author = new Author()
        bindData(author, props)
        author.user = user
        author.save(flush: flush)
        author
    }

    @Transactional
    def update(Author author, Map props, boolean flush = false) {
        bindData(author, props)
        author.save(flush: flush)
        author
    }

    @Transactional
    def delete(Long authorId, boolean flush = false) {
        def author = Author.load(authorId)
        author.delete()
    }

    @Transactional
    def delete(Author author, boolean flush = false) {
        delete(author.id, flush)
    }
}
