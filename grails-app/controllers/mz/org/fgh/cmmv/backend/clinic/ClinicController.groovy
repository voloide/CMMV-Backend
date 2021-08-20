package mz.org.fgh.cmmv.backend.clinic

import grails.converters.JSON
import grails.rest.RestfulController
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

class ClinicController extends RestfulController{

    ClinicService clinicService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    ClinicController() {
        super(Clinic)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        JSON.use('deep'){
            render clinicService.list(params) as JSON
        }
    }

    def show(Long id) {
        JSON.use('deep'){
            render clinicService.get(id) as JSON
        }
    }


    @Transactional
    def save(Clinic clinic) {
        if (clinic == null) {
            render status: NOT_FOUND
            return
        }
        if (clinic.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clinic.errors
            return
        }

        try {
            clinicService.save(clinic)
        } catch (ValidationException e) {
            respond clinic.errors
            return
        }

        respond clinic, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Clinic clinic) {
        if (clinic == null) {
            render status: NOT_FOUND
            return
        }
        if (clinic.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clinic.errors
            return
        }

        try {
            clinicService.save(clinic)
        } catch (ValidationException e) {
            respond clinic.errors
            return
        }

        respond clinic, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || clinicService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
