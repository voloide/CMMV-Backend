package mz.org.fgh.cmmv.backend.mobilizer

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class CommunityMobilizerController {

    CommunityMobilizerService communityMobilizerService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond communityMobilizerService.list(params), model:[communityMobilizerCount: communityMobilizerService.count()]
    }

    def show(Long id) {
        respond communityMobilizerService.get(id)
    }

    @Transactional
    def save(CommunityMobilizer communityMobilizer) {
        if (communityMobilizer == null) {
            render status: NOT_FOUND
            return
        }
        if (communityMobilizer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityMobilizer.errors
            return
        }

        try {
            communityMobilizerService.save(communityMobilizer)
        } catch (ValidationException e) {
            respond communityMobilizer.errors
            return
        }

        respond communityMobilizer, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(CommunityMobilizer communityMobilizer) {
        if (communityMobilizer == null) {
            render status: NOT_FOUND
            return
        }
        if (communityMobilizer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond communityMobilizer.errors
            return
        }

        try {
            communityMobilizerService.save(communityMobilizer)
        } catch (ValidationException e) {
            respond communityMobilizer.errors
            return
        }

        respond communityMobilizer, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || communityMobilizerService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
