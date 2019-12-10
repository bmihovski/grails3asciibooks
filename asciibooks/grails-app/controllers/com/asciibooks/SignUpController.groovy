package com.asciibooks

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured("permitAll")
class SignUpController {

    def join() {}

    @Transactional
    def save(SignUpCommand cmd) {
        if (!cmd.validate()) {
            flash.errors = cmd.errors
            redirect(action: 'join')
            return
        }

        def user = new User(email: cmd.email, password: cmd.password)
        def author = new Author(user: user)
        bindData(author, cmd.author, ['user'])
        author.save()
        user.save()
        UserRole.create(user, Role.findByAuthority('ROLE_AUTHOR'), false)
        flash.message = "Login with your email and password to continue."
        redirect(controller: 'login')
    }

}

    class SignUpCommand {
        String email
        String password
        Author author

        static constraints = {
            email email:true
        }

    }
