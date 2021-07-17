package mz.org.fgh.cmmv.backend.link

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class LinkController {

    LinkService linkService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond linkService.list(params), model:[linkCount: linkService.count()]
    }

    def show(Long id) {
        respond linkService.get(id)
    }

    @Transactional
    def save(Link link) {
        if (link == null) {
            render status: NOT_FOUND
            return
        }
        if (link.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond link.errors
            return
        }

        try {
            linkService.save(link)
        } catch (ValidationException e) {
            respond link.errors
            return
        }

        respond link, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Link link) {
        if (link == null) {
            render status: NOT_FOUND
            return
        }
        if (link.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond link.errors
            return
        }

        try {
            linkService.save(link)
        } catch (ValidationException e) {
            respond link.errors
            return
        }

        respond link, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || linkService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
