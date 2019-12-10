package com.asciibooks

import com.asciibooks.commands.PaginationCommand
import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.CREATED

@Secured("ROLE_ADMIN")
class AuthorController {

    def authorService
    def springSecurityService

    def show(Long Id) {
        def author = authorService.getAuthor(id)
        respond author
    }

    def index(PaginationCommand paginationCommand) {
        def authors = authorService.getAuthors(paginationCommand.params)

        respond([authors: authors, total: authors?.totalCount, offset: paginationCommand.offset])
    }

    def save() {
        User user = springSecurityService.loadCurrentUser()
        Author author = authorService.save(request.JSON as Map, user)

        if (author.hasErrors()) {
            respond author.errors
        } else {
            response.status = CREATED.value()
        }

    }

}
