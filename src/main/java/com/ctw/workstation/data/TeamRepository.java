package com.ctw.workstation.data;

import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public final class TeamRepository implements PanacheRepository<Team> {

    public TeamRepository() {}

}
