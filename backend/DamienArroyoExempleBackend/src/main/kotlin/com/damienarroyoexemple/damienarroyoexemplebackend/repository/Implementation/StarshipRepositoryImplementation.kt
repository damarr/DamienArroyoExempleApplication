package com.damienarroyoexemple.damienarroyoexemplebackend.repository.Implementation;

import com.beust.klaxon.Klaxon
import com.damienarroyoexemple.damienarroyoexemplebackend.model.StarshipDatabaseModel
import com.damienarroyoexemple.damienarroyoexemplebackend.model.Starship
import com.damienarroyoexemple.damienarroyoexemplebackend.model.StarshipList
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class StarshipRepositoryImplementation {

    private val client = OkHttpClient();
    private val baseUrl = "https://swapi.dev/api/starships/";
    private var fullDataString = StarshipDatabaseModel();

    private fun runRequest(url : String?): String{
        val request = Request.Builder().url(url!!).build();

        client.newCall(request).execute().use{
                response ->
            if (!response.isSuccessful) throw IOException("Unexpected exception $response");

            for ((name, value) in response.headers) {
                println("$name: $value");
            }

            return response.body!!.string();
        }
    }

    private fun assignDataToDatabase(count: Int, results: List<Starship>?) {
        if (results != null) {
            fullDataString.results.addAll(results)
        };
    }

    fun fetchAllData(){
        var response = runRequest(baseUrl);
        var parsedResponse = parseData(response);

        if (parsedResponse != null) {
            parsedResponse.count?.let { assignDataToDatabase(it, parsedResponse!!.results) }
        }

        while (parsedResponse !== null && parsedResponse.next !== null) {
            response = runRequest(parsedResponse.next!!);
            parsedResponse = parseData(response);

            if (parsedResponse != null) {
                parsedResponse.count?.let { assignDataToDatabase(it, parsedResponse.results) }
            }

        }
    }

    private fun parseData(response: String) : StarshipList? {
        return Klaxon().parse<StarshipList>(response);
    }


    fun getAllStarShips(): List<Starship>{
        return fullDataString.results;
    }
}