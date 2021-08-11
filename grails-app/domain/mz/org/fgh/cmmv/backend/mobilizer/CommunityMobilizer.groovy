package mz.org.fgh.cmmv.backend.mobilizer

import grails.rest.Resource
import mz.org.fgh.cmmv.backend.clinic.Clinic
import mz.org.fgh.cmmv.backend.docsOrImages.InfoDocsOrImages
import mz.org.fgh.cmmv.backend.utente.Utente

@Resource(uri='/api/communityMobilizer')
class CommunityMobilizer{

    String firstNames
    String lastNames
    String cellNumber
    String cellNumber2
    String uuid
  
    static hasMany = [docsOrImages: InfoDocsOrImages, utentes:Utente]
    static belongsTo = [clinic : Clinic]
//    static belongsTo = [docsOrImages: InfoDocsOrImages , clinic : Clinic]

    static constraints = {
        firstNames(nullable: false, blank: false)
        lastNames(nullable: false, blank: false)
        cellNumber(nullable: false, matches: /\d+/, maxSize: 12, minSize: 9)
        cellNumber2(nullable: true, matches: /\d+/, maxSize: 12, minSize: 9)
        user(nullable: true)
    }
}
