package com.ctw.workstation.services;

import com.ctw.workstation.data.RackRepository;
import com.ctw.workstation.rack.dto.RackRequest;
import com.ctw.workstation.rack.dto.RackResponse;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.dto.TeamResponse;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.dto.TeamMemberResponse;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RackService {

    @Inject
    RackRepository repository;
    @Inject
    TeamService teamService;

    private RackService() {
    }

    @Transactional
    public List<RackResponse> getAllRacks() {
        List<Rack> racksList = repository.listAll();
        List<RackResponse> rackResponseList = new ArrayList<>();
        for (Rack rack : racksList) {
            RackResponse rackResponse = convertRackToRackResponse(rack);
            rackResponseList.add(rackResponse);
        }
        return rackResponseList;
    }

    @Transactional
    public List<Rack> getFilteredRacks(String status) {
        if (status == null) {
            return repository.listAll();
        } else {
            return repository.findAll().stream().filter(r -> r.getStatus().equals(status)).collect(Collectors.toList());
        }
    }

    @Transactional
    public Rack getRack(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public RackResponse createRack(RackRequest rackRequest) {
        Rack newRack = new Rack.Builder()
                .setDefaultLocation(rackRequest.getDefaultLocation())
                .setSerialNumber(rackRequest.getSerialNumber())
                .setStatus(rackRequest.getStatus())
                .build();

        rackRequest.getTeamId().ifPresent(teamId -> {
            Team team = teamService.getTeam(teamId);
            newRack.setTeam(team);
        });

        repository.persistAndFlush(newRack);
        return convertRackToRackResponse(newRack);
    }

    @Transactional
    public RackResponse updateRack(RackRequest rackRequest, Long id) {
        Rack rack = repository.findById(id);
        rack.setSerialNumber(rackRequest.getSerialNumber());
        rack.setStatus(rackRequest.getStatus());
        rack.setDefaultLocation(rackRequest.getDefaultLocation());

        rackRequest.getTeamId().ifPresent(teamId -> {
            Team team = teamService.getTeam(teamId);
            rack.setTeam(team);
        });

        repository.persistAndFlush(rack);
        return convertRackToRackResponse(rack);
    }

    @Transactional
    public void removeRack(Rack rack) {
        repository.delete(rack);
    }

    private RackResponse convertRackToRackResponse(Rack rack) {
        RackResponse rackResponse = new RackResponse();

        rackResponse.setId(rack.getId());
        rackResponse.setSerialNumber(rack.getSerialNumber());
        rackResponse.setStatus(rack.getStatus());
        rackResponse.setDefaultLocation(rack.getDefaultLocation());

        if (rack.getTeam() != null) {
            rackResponse.setTeamName(rack.getTeam().getName());
        }

        return rackResponse;
    }
}
