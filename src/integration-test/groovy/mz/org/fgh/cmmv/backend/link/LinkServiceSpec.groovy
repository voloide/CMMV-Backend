package mz.org.fgh.cmmv.backend.link

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class LinkServiceSpec extends Specification {

    LinkService linkService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Link(...).save(flush: true, failOnError: true)
        //new Link(...).save(flush: true, failOnError: true)
        //Link link = new Link(...).save(flush: true, failOnError: true)
        //new Link(...).save(flush: true, failOnError: true)
        //new Link(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //link.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        linkService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Link> linkList = linkService.list(max: 2, offset: 2)

        then:
        linkList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        linkService.count() == 5
    }

    void "test delete"() {
        Long linkId = setupData()

        expect:
        linkService.count() == 5

        when:
        linkService.delete(linkId)
        datastore.currentSession.flush()

        then:
        linkService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Link link = new Link()
        linkService.save(link)

        then:
        link.id != null
    }
}
