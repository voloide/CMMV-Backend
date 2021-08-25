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

    static mapping = {
        //code type: GormEncryptedStringType
        //description type: GormEncryptedStringType
//        birthDate type: GormEncryptedDateAsStringType
//        anniversary type: GormEncryptedCalendarAsStringType
//        hasInsurance type: GormEncryptedBooleanType
//        latitude type: GormEncryptedDoubleAsStringType
//        cashBalance type: GormEncryptedFloatAsStringType
//        bdBalance type: GormEncryptedBigDecimalType
//        bdBalance type: GormEncryptedBigDecimalAsStringType
//        weight type: GormEncryptedShortAsStringType
//        height type: GormEncryptedIntegerAsStringType
//        patientId type: GormEncryptedLongAsStringType
//        biteMe type: GormEncryptedByteAsStringType
    }
}
