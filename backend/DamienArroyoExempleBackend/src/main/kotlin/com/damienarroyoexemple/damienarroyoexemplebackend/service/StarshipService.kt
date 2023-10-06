package com.damienarroyoexemple.damienarroyoexemplebackend.service

import com.damienarroyoexemple.damienarroyoexemplebackend.model.Starship

interface StarshipService {
    fun getAllStarships(): List<Starship>
}