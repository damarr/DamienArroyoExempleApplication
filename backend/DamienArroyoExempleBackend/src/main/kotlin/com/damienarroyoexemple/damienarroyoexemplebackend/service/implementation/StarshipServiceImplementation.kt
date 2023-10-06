package com.damienarroyoexemple.damienarroyoexemplebackend.service.implementation

import com.damienarroyoexemple.damienarroyoexemplebackend.model.Starship
import com.damienarroyoexemple.damienarroyoexemplebackend.repository.Implementation.StarshipRepositoryImplementation
import com.damienarroyoexemple.damienarroyoexemplebackend.service.StarshipService
import org.springframework.stereotype.Service

@Service
class StarshipServiceImplementation() : StarshipService {
    private final val starshipRepositoryImplementation = StarshipRepositoryImplementation();

    init{
        starshipRepositoryImplementation.fetchAllData();
    }

    override fun getAllStarships(): List<Starship> {
        return starshipRepositoryImplementation.getAllStarShips();
    }
}