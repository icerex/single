import grails.util.Environment

// Place your Spring DSL code here
beans = {

    switch (Environment.current) {
        case Environment.PRODUCTION:
            break
        case Environment.DEVELOPMENT:
        case Environment.TEST:
            break
    }

}
