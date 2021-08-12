package mz.org.fgh.cmmv.backend.distribuicaoAdministrativa

class Province {

    String code
    String description

    static hasMany = [districts: District]

    static constraints = {
        code nullable: false, unique: true
        description nullable: false
    }
}
