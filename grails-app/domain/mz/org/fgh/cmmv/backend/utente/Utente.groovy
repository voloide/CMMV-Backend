package mz.org.fgh.cmmv.backend.utente


import mz.org.fgh.cmmv.backend.address.Address
import mz.org.fgh.cmmv.backend.clinic.Clinic
import mz.org.fgh.cmmv.backend.docsOrImages.InfoDocsOrImages
import mz.org.fgh.cmmv.backend.mobilizer.CommunityMobilizer
import mz.org.fgh.cmmv.backend.userLogin.UserLogin

class Utente {

    String firstNames
    String lastNames
    Date birthDate
    String cellNumber
    String whatsappNumber
    String preferedLanguage
    String documentType
    String documentNumber
    String systemNumber
    CommunityMobilizer mobilizer
    UserLogin user
    boolean haspartner

    static belongsTo = [clinic: Clinic]

    static hasMany = [infoDocsImages: InfoDocsOrImages, address:Address]

    static constraints = {
        lastNames(nullable: false, blank: false)
        birthDate(nullable: false, blank: true, validator: { birthDate, urc ->
            return birthDate != null ? birthDate <= new Date() : null
        })
        cellNumber(nullable: false, matches: /\d+/, maxSize: 12, minSize: 9)
        whatsappNumber(nullable: false, matches: /\d+/, maxSize: 12, minSize: 9)
        documentType(nullable: false, blank: false)
        documentNumber(nullable: false, blank: false)
        systemNumber(nullable: false, blank: false)
        preferedLanguage(nullable: true)
        infoDocsImages(nullable: true)
        mobilizer(nullable: true)
        clinic(nullable: true)
        user(nullable: true)
    }
}
