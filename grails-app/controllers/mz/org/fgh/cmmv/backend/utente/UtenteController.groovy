package mz.org.fgh.cmmv.backend.utente

import grails.converters.JSON
import grails.rest.RestfulController
import grails.validation.ValidationException
import mz.org.fgh.cmmv.backend.clinic.Clinic
import mz.org.fgh.cmmv.backend.clinic.ClinicController
import mz.org.fgh.cmmv.backend.clinic.ClinicService
import mz.org.fgh.cmmv.backend.userLogin.UtenteLogin
import org.apache.groovy.util.Maps
import org.h2.value.Value

import javax.persistence.EntityNotFoundException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

class UtenteController extends RestfulController{

    UtenteService utenteService
    ClinicService clinicService

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
            utente.setUser(new UtenteLogin())
            utente.getUser().setUsername(utente.getFirstNames())
            utente.getUser().setPassword(utente.getLastNames())
            utente.getUser().setUtente(utente)
            utente.setSystemNumber(utente.getFirstNames().substring(0,1)+utente.getLastNames().substring(0,1)+"-"+utente.getCellNumber())
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

   /* @Transactional
    def associateUtents (List<Utente> utentes, CommunityMobilizer mobilizer) {
        // utenteService.associateUtents(utentes,mobilizer)
        utentes.each { utente ->
          utente.clinic=mobilizer.clinic
            utente.clinicId=mobilizer.clinicId
            utente.mobilizer=mobilizer
            utente.mobilizerId=mobilizer.id
            utente.status="ASSOCIADO"
            utente.update(utente)
            //utenteService.associateUtents(utentes,mobilizer)
        }*/


        @Transactional
        def updateUtentes (List<Utente> users) {
            try {
                Utente.saveAll(users)
            } catch (ValidationException e) {
                respond users.errors
                return
            }
            respond users, [status: OK, view:"show"]
        }

    def searchByClinicId(Long id){
        JSON.use('deep'){
                Clinic clinic = clinicService.get(id)
                // println ('id da clinica'+clinic.getId())
                  //  println ('id da clinica2'+Utente.findAllByClinic(clinic))
                    render Utente.findAllByClinic(clinic) as JSON
        }
    }
}
