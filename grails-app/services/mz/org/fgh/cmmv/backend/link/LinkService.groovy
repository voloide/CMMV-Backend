package mz.org.fgh.cmmv.backend.link

import grails.gorm.services.Service

@Service(Link)
interface LinkService {

    Link get(Serializable id)

    List<Link> list(Map args)

    Long count()

    Link delete(Serializable id)

    Link save(Link link)

}
