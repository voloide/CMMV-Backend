package mz.org.fgh.cmmv.backend.docsOrImages

import grails.rest.Resource
import mz.org.fgh.cmmv.backend.mobilizer.CommunityMobilizer
import mz.org.fgh.cmmv.backend.utente.Utente

@Resource(uri='/api/infoDocsOrImages')
class InfoDocsOrImages {

    String title
    Date createdDate
    Date publishedDate
    byte blop

    static hasMany = [users: Utente, mobilizers: CommunityMobilizer]
    static belongsTo = [users: Utente]


    static constraints = {

        title nullable: false
        createdDate(nullable: false, blank: false, validator: { createdDate, urc ->
            return createdDate != null ? createdDate <= new Date() : null
        })
        publishedDate(nullable: false, blank: false)

    }
}
