TestDataConfig {
    sampleData {
        'com.asciibooks.User' {
            def i = 1
            // ensure emails are generated uniquely
            email = {-> "email${i++}@example.com"}
            // have strong password that passes custom validator.
            password = "StrongPass!342"
        }
    }
}
