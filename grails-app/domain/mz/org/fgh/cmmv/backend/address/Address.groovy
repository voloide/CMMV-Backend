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
        neighboorhood(nullable: true, blank: true)
        city(nullable: true, blank: true)
        residence(nullable: false, blank: false)
        latitude(nullable: false, blank: false)
        longitude(nullable: false,blank: false)
        utente nullable: true
    }
}
