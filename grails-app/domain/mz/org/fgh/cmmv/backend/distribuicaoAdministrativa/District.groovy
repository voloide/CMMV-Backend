package mz.org.fgh.cmmv.backend.distribuicaoAdministrativa

import com.bloomhealthco.jasypt.GormEncryptedStringType

class District {

    String code
    String description

    static belongsTo = [province: Province]

    static constraints = {
        code nullable: false, unique: ['province']
        description nullable: false
    }

    static mapping = {
        code type: GormEncryptedStringType
        description type: GormEncryptedStringType
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
