package com.example.dispositivimobili

class Movie {

    var title : String
    var description : String
    var posterUrl = "https://image.tmdb.org/t/p/w500"
    var id : String = ""

    constructor(newTitle : String, newDescription : String, newUrl : String, newId : String) {
        title = newTitle
        description = newDescription
        posterUrl += newUrl
        id = newId
    }




}