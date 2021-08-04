package mz.org.fgh.cmmv.backend.userLogin

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class UserLoginController {

    UserLoginService userLoginService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userLoginService.list(params), model:[userLoginCount: userLoginService.count()]
    }

    def show(Long id) {
        respond userLoginService.get(id)
    }

    @Transactional
    def save(UserLogin userLogin) {
        if (userLogin == null) {
            render status: NOT_FOUND
            return
        }
        if (userLogin.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLogin.errors
            return
        }

        try {
            userLoginService.save(userLogin)
        } catch (ValidationException e) {
            respond userLogin.errors
            return
        }

        respond userLogin, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(UserLogin userLogin) {
        if (userLogin == null) {
            render status: NOT_FOUND
            return
        }
        if (userLogin.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLogin.errors
            return
        }

        try {
            userLoginService.save(userLogin)
        } catch (ValidationException e) {
            respond userLogin.errors
            return
        }

        respond userLogin, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || userLoginService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}