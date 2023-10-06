package com.damienarroyoexemple.damienarroyoexemplebackend.repository

import com.damienarroyoexemple.damienarroyoexemplebackend.model.Starship
import org.springframework.data.jpa.repository.JpaRepository

interface StarshipRepository : JpaRepository<Starship, String>{

}