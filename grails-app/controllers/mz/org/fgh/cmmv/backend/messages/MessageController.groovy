package mz.org.fgh.cmmv.backend.messages

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class MessageController {

    MessageService messageService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond messageService.list(params), model:[messageCount: messageService.count()]
    }

    def show(Long id) {
        respond messageService.get(id)
    }

    @Transactional
    def save(Message message) {
        if (message == null) {
            render status: NOT_FOUND
            return
        }
        if (message.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond message.errors
            return
        }

        try {
            messageService.save(message)
        } catch (ValidationException e) {
            respond message.errors
            return
        }

        respond message, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Message message) {
        if (message == null) {
            render status: NOT_FOUND
            return
        }
        if (message.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond message.errors
            return
        }

        try {
            messageService.save(message)
        } catch (ValidationException e) {
            respond message.errors
            return
        }

        respond message, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || messageService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
