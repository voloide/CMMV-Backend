package mz.org.fgh.cmmv.backend.distribuicaoAdministrativa

import grails.rest.Resource

@Resource(uri='/api/province')
class Province {

    String code
    String description

    static hasMany = [districts: District]

    static constraints = {
        code nullable: false, unique: true
        description nullable: false
    }
}
