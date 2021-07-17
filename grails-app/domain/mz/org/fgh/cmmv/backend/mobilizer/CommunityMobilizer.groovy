package mz.org.fgh.cmmv.backend.mobilizer

import grails.rest.Resource
import mz.org.fgh.cmmv.backend.docsOrImages.InfoDocsOrImages
import mz.org.fgh.cmmv.backend.messages.Message

@Resource(uri='/api/communityMobilizer')
class CommunityMobilizer {

    String firstNames
    String lastNames
    String uuid

    static hasMany = [docsOrImages: InfoDocsOrImages]
    static belongsTo = [docsOrImages: InfoDocsOrImages]

    static constraints = {
        firstNames(nullable: false, blank: false)
        lastNames(nullable: false, blank: false)
    }
}
