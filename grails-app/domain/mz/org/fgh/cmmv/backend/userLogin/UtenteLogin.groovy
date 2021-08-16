package mz.org.fgh.cmmv.backend.userLogin

import mz.org.fgh.cmmv.backend.utente.Utente

class UtenteLogin extends UserLogin{

    static belongsTo = [utente: Utente]
    static constraints = {
        utente nullable: true
    }
}
