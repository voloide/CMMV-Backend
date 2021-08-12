package mz.org.fgh.cmmv.backend.address

import grails.rest.Resource
import mz.org.fgh.cmmv.backend.distribuicaoAdministrativa.District
import mz.org.fgh.cmmv.backend.utente.Utente

class Address {

    String neighboorhood
    String city
    String residence
    String latitude
    String longitude
    static belongsTo = [district: District]
    static hasOne = [user: Utente]
    static constraints = {

        neighboorhood(nullable: false, blank: false)
        city(nullable: false, blank: false)
        residence(nullable: false, blank: false)
        latitude(nullable: false, blank: false)
        longitude(nullable: false,blank: false)
        user nullable: true
    }
}
