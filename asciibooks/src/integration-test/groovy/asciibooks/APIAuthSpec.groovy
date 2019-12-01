package asciibooks

import com.asciibooks.Role
import com.asciibooks.User
import com.asciibooks.UserRole
import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*

@Integration
@Rollback
class APIAuthSpec extends Specification {

    @Shared RestBuilder rest
    @Shared String baseUrl

    // tag::setupAPISpec[]
    void setup() {
        rest = new RestBuilder()
        baseUrl = "http://localhost:${serverPort}"

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError:true)
        def authorRole = new Role(authority: 'ROLE_AUTHOR').save(failOnError:true)

        def admin = new User(email: "admin@asciibooks.com", password: 'AStrongPass!23').save(failOnError:true)
        def author = new User(email: "author@grails3book.com", password: 'ADifferentStrongPass!23').save(failOnError:true)
        def authorCaseInsensitive = new User(email: "BOB@grails3book.com", password: 'YetAnotherStrong!23').save(failOnError:true)

        UserRole.create(admin, adminRole)
        UserRole.create(author, authorRole)
        UserRole.create(authorCaseInsensitive, authorRole)
    }
    // end::setupAPISpec[]

    // tag::APILogin[]
    def cleanup() {
        UserRole.executeUpdate 'delete UserRole'
        User.executeUpdate 'delete User'
        Role.executeUpdate 'delete Role'
    }

    @Unroll
    void "login with #emailAddress"() {
        when:
        def resp = rest.post("${baseUrl}/api/login") {
            json {
                email = emailAddress
                password = userPassword
            }
        }
        def contentType = resp.headers.getContentType()

        then:
        resp.status == OK.value()
        contentType.subtype == 'json'
        contentType.type == 'application'

        and:
        resp.json.username.toLowerCase() == emailAddress.toLowerCase()
        and:
        resp.json.access_token
        resp.json.refresh_token
        resp.json.token_type == "Bearer"
        resp.json.expires_in == 3600

        where:
        emailAddress             | userPassword
        "admin@asciibooks.com"   | "AStrongPass!23"
        "author@grails3book.com" | "ADifferentStrongPass!23"
        "bob@grails3book.com"    | "YetAnotherStrong!23"
        "BOB@grails3book.com"    | "YetAnotherStrong!23"
    }
    // end::APILogin[]

    // tag::APILoginBad[]
    void "login with bad creds"() {
        when:
        def resp = rest.post("${baseUrl}/api/login") {
            json {
                email = "bad"
                password = "bad"
            }
        }
        def contentType = resp.headers.contentType

        then:
        resp.status == UNAUTHORIZED.value()
        !contentType

        and:
        !resp.json
    }
    // end::APILoginBad[]

    // tag::APILoginBadMethod[]
    @Unroll
    void "Cant use #method on /api/login"() {
        when:
        def resp = rest."$method"("${baseUrl}/api/login")
        def contentType = resp.headers.contentType

        then:
        resp.status == METHOD_NOT_ALLOWED.value()
        !contentType

        and:
        !resp.json

        where:
        method << ["get", "put", "delete"]
    }
    // end::APILoginBadMethod[]
}