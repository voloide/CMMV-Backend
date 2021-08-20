package mz.org.fgh.cmmv.backend.utente

import grails.converters.JSON
import grails.rest.RestfulController
import grails.validation.ValidationException
import org.apache.groovy.util.Maps
import org.h2.value.Value

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

class UtenteController extends RestfulController{

    UtenteService utenteService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    UtenteController() {
        super(Utente)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        JSON.use('deep'){
            render utenteService.list(params) as JSON
        }
    }

    def show(Long id) {
        JSON.use('deep'){
            render utenteService.get(id) as JSON
        }
    }

    def search(String systemNumber){
        JSON.use('deep'){
            render Utente.findBySystemNumber(systemNumber) as JSON
        }
    }

    @Transactional
    def save(Utente utente) {
        if (utente == null) {
            render status: NOT_FOUND
            return
        }
        if (utente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond utente.errors
            return
        }

        try {
            utenteService.save(utente)
        } catch (ValidationException e) {
            respond utente.errors
            return
        }

        respond utente, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Utente utente) {
        if (utente == null) {
            render status: NOT_FOUND
            return
        }
        if (utente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond utente.errors
            return
        }

        try {
            utenteService.save(utente)
        } catch (ValidationException e) {
            respond utente.errors
            return
        }

        respond utente, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || utenteService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
