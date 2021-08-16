package mz.org.fgh.cmmv.backend.userLogin

import mz.org.fgh.cmmv.backend.mobilizer.CommunityMobilizer

class MobilizerLogin extends UserLogin{
    static belongsTo = [mobilizer: CommunityMobilizer]

    static constraints = {
        mobilizer nullable: true
    }
}
