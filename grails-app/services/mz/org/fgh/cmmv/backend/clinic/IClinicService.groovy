package mz.org.fgh.cmmv.backend.clinic

interface IClinicService {

    Clinic get(Serializable id)

    List<Clinic> list(Map args)

    Long count()

    Clinic delete(Serializable id)

    Clinic save(Clinic clinic)

    List<Clinic> searchByDistance(double lat, double log, double distance)

}
