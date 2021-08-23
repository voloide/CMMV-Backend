package cmmv.backend

class UrlMappings {

    static mappings = {
        delete "/api/$controller/$id(.$format)?"(action:"delete")
        get "/api/$controller(.$format)?"(action:"index")
        get "/api/$controller/$id(.$format)?"(action:"show")
        post "/api/$controller(.$format)?"(action:"save")
        put "/api/$controller/$id(.$format)?"(action:"update")
        patch "/api/$controller/$id(.$format)?"(action:"patch")

        // add mapping for Utente search by param systemNumber
         get "/api/utente/$systemNumber(.$format)?"(controller:'utente', action:'search')
        get "/api/utente/clinic/$id(.$format)?"(controller:'utente', action:'searchByClinicId')
        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
