package mz.org.fgh.cmmv.backend.distribuicaoAdministrativa

import mz.org.fgh.cmmv.backend.address.Address

class District {

    String code
    String description

    static hasMany = [addresses: Address]
    static belongsTo = [province: Province]

    static constraints = {
        code nullable: false, unique: ['province']
        description nullable: false
    }
}
