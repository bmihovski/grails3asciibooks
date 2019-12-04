package com.asciibooks.urlmappings

import static org.grails.web.mapping.DefaultUrlMappingEvaluator.*

class APIUrlMappings {
    static mappings = {
        group "/api", {
            group "/v1", {
                "books"(resources: "book") {
                    "/author"(resources: "author", includes: [ACTION_SHOW])
                }
                "/authors"(resources: "author") {
                    "/books"(resources: "book", includes: [ACTION_SHOW, ACTION_INDEX])
                }
            }
        }
    }
}
