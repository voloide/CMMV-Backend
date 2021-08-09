package mz.org.fgh.cmmv.backend.userLogin

import grails.rest.Resource
import mz.org.fgh.cmmv.backend.mobilizer.CommunityMobilizer
import mz.org.fgh.cmmv.backend.utente.Utente

@Resource(uri='/api/login')
class UserLogin {

    String username
    String password


    static belongsTo = [utente: Utente, mobilizador: CommunityMobilizer]

    static constraints = {
        username(nullable: false, blank: false)
        password(nullable: false, blank: false)
    }
}
