package mz.org.fgh.cmmv.backend.distribuicaoAdministrativa

import grails.converters.JSON
import grails.rest.RestfulController
import grails.validation.ValidationException
import sun.misc.Resource

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

class ProvinceController extends RestfulController{

    ProvinceService provinceService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    ProvinceController() {
        super(Province)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        JSON.use('deep'){
            render provinceService.list(params) as JSON
        }
    }

    def show(Long id) {
        JSON.use('deep'){
            render provinceService.get(id) as JSON
        }
    }

    @Transactional
    def save(Province province) {
        if (province == null) {
            render status: NOT_FOUND
            return
        }
        if (province.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond province.errors
            return
        }

        try {
            provinceService.save(province)
        } catch (ValidationException e) {
            respond province.errors
            return
        }

        respond province, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Province province) {
        if (province == null) {
            render status: NOT_FOUND
            return
        }
        if (province.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond province.errors
            return
        }

        try {
            provinceService.save(province)
        } catch (ValidationException e) {
            respond province.errors
            return
        }

        respond province, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || provinceService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
