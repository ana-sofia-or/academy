package com.ctw.workstation.data;

import com.ctw.workstation.rack.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public final class RackRepository implements PanacheRepository<Rack> {

    public RackRepository() {}

}
