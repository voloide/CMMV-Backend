package mz.org.fgh.cmmv.backend.clinic

import mz.org.fgh.cmmv.backend.appointment.Appointment
import mz.org.fgh.cmmv.backend.mobilizer.CommunityMobilizer

class Clinic {

    String code
    String name
    String type
    String latitude
    String longitude

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
