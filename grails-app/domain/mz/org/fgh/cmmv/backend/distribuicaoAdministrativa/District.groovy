package mz.org.fgh.cmmv.backend.distribuicaoAdministrativa

class District {

    String code
    String description

    static belongsTo = [province: Province]

    static constraints = {
        code nullable: false, unique: ['province']
        description nullable: false
    }
}
