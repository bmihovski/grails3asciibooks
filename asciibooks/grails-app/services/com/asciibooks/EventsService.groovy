package com.asciibooks

import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector

@Consumer
class EventsService {

    @Selector('author.signup')
    def sendNewUserEmail(email) {
        log.info"Sending welcome email to {}", email
    }

    @Selector('author.signup')
    def sendNewUserSlack(email) {
        log.info"Ping Slack new user: {}", email
    }

}
