package com.ctw.workstation.data;

import com.ctw.workstation.rackasset.entity.RackAsset;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RackAssetRepository implements PanacheRepository<RackAsset> {

    public RackAssetRepository() {}

}
