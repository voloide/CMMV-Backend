package mz.org.fgh.cmmv.backend.mobilizer

import mz.org.fgh.cmmv.backend.clinic.Clinic
import mz.org.fgh.cmmv.backend.docsOrImages.InfoDocsOrImages
import mz.org.fgh.cmmv.backend.userLogin.MobilizerLogin
import mz.org.fgh.cmmv.backend.utente.Utente

class CommunityMobilizer{

    String firstNames
    String lastNames
    String cellNumber
    String cellNumber2
    String uuid

    static hasMany = [docsOrImages: InfoDocsOrImages, utentes:Utente]
    static belongsTo = [clinic : Clinic]
    static hasOne = [user: MobilizerLogin]

    static constraints = {
        firstNames(nullable: false, blank: false)
        lastNames(nullable: false, blank: false)
        cellNumber(nullable: false, matches: /\d+/, maxSize: 12, minSize: 9)
        cellNumber2(nullable: true, matches: /\d+/, maxSize: 12, minSize: 9)
        user(nullable: true)
    }
}
