package mz.org.fgh.cmmv.backend.link

import grails.rest.Resource
import mz.org.fgh.cmmv.backend.clinic.Clinic
import mz.org.fgh.cmmv.backend.mobilizer.CommunityMobilizer
import mz.org.fgh.cmmv.backend.utente.Utente


@Resource(uri='/api/link')
class Link {

    Date dateLink

    static belongsTo = [mobilizer: CommunityMobilizer,clinic: Clinic,user: Utente]

    static constraints = {
        dateLink(nullable: false, blank: true, validator: { dateLink, urc ->
            return dateLink != null ? dateLink <= new Date() : null
        })
    }
}
