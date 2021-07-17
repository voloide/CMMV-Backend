package mz.org.fgh.cmmv.backend.clinic

import grails.rest.Resource
import mz.org.fgh.cmmv.backend.appointment.Appointment
import mz.org.fgh.cmmv.backend.link.Link

@Resource(uri='/api/clinic')
class Clinic {

    String code
    String name
    String type
    String latitude
    String longitude

    static hasMany = [
            appointments: Appointment,
            links: Link
    ]

    static constraints = {
        code nullable: false, unique: true
        name nullable: false
        type nullable: false
        latitude nullable: false
        longitude nullable: false
    }
}
