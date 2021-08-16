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
    static belongsTo = [utente: Utente, district: District]

    static constraints = {
        neighboorhood(nullable: false, blank: false)
        city(nullable: false, blank: false)
        residence(nullable: false, blank: false)
        latitude(nullable: false, blank: false)
        longitude(nullable: false,blank: false)
        utente nullable: true
    }
}
