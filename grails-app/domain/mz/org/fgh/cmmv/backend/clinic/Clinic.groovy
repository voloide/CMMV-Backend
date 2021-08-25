package mz.org.fgh.cmmv.backend.clinic

import com.fasterxml.jackson.annotation.JsonInclude
import mz.org.fgh.cmmv.backend.appointment.Appointment
import mz.org.fgh.cmmv.backend.mobilizer.CommunityMobilizer

import javax.persistence.Transient

class Clinic {

    String code
    String name
    String type
    double latitude
    double longitude
    @JsonInclude
    double distance

    static transients = ['distance']

    static hasMany = [
            appointments: Appointment,
            mobilizers: CommunityMobilizer
    ]

    static constraints = {
        code nullable: false, unique: true
        name nullable: false
        type nullable: false
        latitude nullable: false
        longitude nullable: false
    }
}
