package com.ctw.workstation.services;

import com.ctw.workstation.data.RackAssetRepository;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rackasset.dto.RackAssetRequest;
import com.ctw.workstation.rackasset.entity.RackAsset;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class RackAssetService {
    @Inject
    RackAssetRepository repository;
    @Inject
    RackService rackService;

    private RackAssetService() { }

    @Transactional
    public List<RackAsset> getRackAssets(){
        return repository.listAll();
    }

    @Transactional
    public RackAsset getRackAsset(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public RackAsset createRackAsset(RackAssetRequest rackAssetRequest) {
        Rack rack = rackService.getRack(rackAssetRequest.getRackId());
        RackAsset rackAsset = new RackAsset.Builder()
                .setAssetTag(rackAssetRequest.getAssetTag())
                .setRack(rack)
                .build();
        repository.persistAndFlush(rackAsset);
        return rackAsset;
    }

    @Transactional
    public RackAsset updateRackAsset(RackAssetRequest rackAssetRequest, Long id) {
        Rack rack = rackService.getRack(rackAssetRequest.getRackId());
        RackAsset rackAsset = repository.findById(id);
        rackAsset.setAssetTag(rackAssetRequest.getAssetTag());
        rackAsset.setRack(rack);
        repository.persistAndFlush(rackAsset);
        return rackAsset;
    }

    @Transactional
    public void removeRackAsset(RackAsset rackAsset) {
        repository.delete(rackAsset);
    }

}
