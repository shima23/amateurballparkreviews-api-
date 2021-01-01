package com.myapp.amateurballparkreviewsapi.domain.repository

import com.myapp.amateurballparkreviewsapi.domain.factory.LeagueFactory
import com.myapp.amateurballparkreviewsapi.domain.model.League
import com.myapp.amateurballparkreviewsapi.domain.model.LeagueScore
import com.myapp.amateurballparkreviewsapi.domain.model.LeagueTeam
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueEntity
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueScoreEntity
import com.myapp.amateurballparkreviewsapi.persistence.repository.league.LeagueEntityRepository
import com.myapp.amateurballparkreviewsapi.persistence.repository.league.LeagueScoreEntityRepository
import com.myapp.amateurballparkreviewsapi.persistence.repository.league.LeagueTeamEntityRepository
import org.springframework.stereotype.Repository

@Repository
class LeagueRepository(private val leagueEntityRepository: LeagueEntityRepository,
                       private val leagueTeamEntityRepository: LeagueTeamEntityRepository,
                       private val leagueScoreEntityRepository: LeagueScoreEntityRepository,
                       private val leagueFactory: LeagueFactory
) {
    /** League */

    fun getLeague(leagueId: Int): League {
        return leagueFactory.createLeagueFromEntity(leagueEntityRepository.findById(leagueId).get())
    }

    fun registerLeague(league: League): League {
        val entity = LeagueEntity(league)
        return leagueFactory.createLeagueFromEntity(leagueEntityRepository.save(entity))
    }

    /** LeagueTeam */

    fun getLeagueTeam(leagueId: Int): List<LeagueTeam> {
        return leagueTeamEntityRepository.findByLeagueId(leagueId).map { entity ->
            leagueFactory.createLeagueTeamFromEntity(entity)
        }
    }

    /** LeagueScore */

    fun registerLeagueScore(leagueScore: LeagueScore) {
        val entity = LeagueScoreEntity(leagueScore)
        leagueScoreEntityRepository.save(entity)
    }

    fun getLeagueScore(leagueId: Int): List<LeagueScore> {
        return leagueScoreEntityRepository.findByLeagueId(leagueId).map { entity ->
            leagueFactory.createLeagueScoreFromEntity(entity)
        }
    }


}
