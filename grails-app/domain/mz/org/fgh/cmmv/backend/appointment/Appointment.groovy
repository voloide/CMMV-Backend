package mz.org.fgh.cmmv.backend.appointment

import mz.org.fgh.cmmv.backend.clinic.Clinic
import mz.org.fgh.cmmv.backend.utente.Utente

class Appointment {

    Date appointmentDate
    long time
    boolean hasHappened
    int orderNumber
    String status
    Date visitDate

    static belongsTo = [clinic: Clinic,user: Utente]

    static constraints = {
        appointmentDate(nullable: false, blank: true, validator: { appointmentDate, urc ->
            return appointmentDate != null ? appointmentDate <= new Date() : null
        })
        time nullable:false
        orderNumber nullable:false
        visitDate(nullable: true, blank: true, validator: { visitDate, urc ->
            return visitDate != null ? visitDate <= new Date() : null
        })
        status nullable: false , inList: ['PENDENTE','CONFIRMADO','REMARCADO']
    }
}
