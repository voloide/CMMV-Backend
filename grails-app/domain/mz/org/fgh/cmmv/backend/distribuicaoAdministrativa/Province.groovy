package mz.org.fgh.cmmv.backend.distribuicaoAdministrativa

import grails.rest.Resource

@Resource(uri='/api/provincia')
class Province {

    String code
    String description

    static constraints = {
        code nullable: false, unique: true
        description nullable: false
    }
}
