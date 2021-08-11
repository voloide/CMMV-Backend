package mz.org.fgh.cmmv.backend.distribuicaoAdministrativa

import grails.rest.Resource

@Resource(uri='/api/district')
class District {

    String code
    String description

    static belongsTo = [province: Province]

    static constraints = {
        code nullable: false, unique: ['province']
        description nullable: false
    }
}
