package com.ipl.dashboard.controller;

import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.repository.MatchRepository;
import com.ipl.dashboard.repository.TeamRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    
    public TeamController(TeamRepository teamRepository,MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository=matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){

        Team team= this.teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName,4));
        return team;
        
    }

    
}
