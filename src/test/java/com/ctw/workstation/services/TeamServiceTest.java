package com.ctw.workstation.services;

import com.ctw.workstation.config.CtwAcademyResource;
import com.ctw.workstation.data.TeamRepository;
import com.ctw.workstation.team.dto.TeamRequest;
import com.ctw.workstation.team.dto.TeamResponse;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

//@QuarkusTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class TeamServiceTest {
//
//    //When we need to have other dependencies
//
//    @Inject
//    TeamService teamService;
//
//    @InjectMock
//    TeamRepository teamRepository;
//
//    @BeforeAll
//    static void setUp() {
////        TeamRepository teamRepository = Mockito.mock(TeamRepository.class);
////        QuarkusMock.installMockForType(teamRepository, TeamRepository.class);
//    }
//
//    @Test
//    @DisplayName("Creating a team")
//    void create_team_throws_exception() {
//        TeamRepository teamRepositoryNew = Mockito.mock(TeamRepository.class);
//        QuarkusMock.installMockForInstance(teamRepositoryNew, teamRepository);
//        Mockito.doThrow(new IllegalArgumentException()).when(teamRepositoryNew).persist(Mockito.any(Team.class));
//        //given
//        TeamRequest teamRequest = new TeamRequest("Dream team","Car","Lisbon");
//        //then
//        assertThatThrownBy(() -> teamService.createTeam(teamRequest)).isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Not possible to create a team");
//    }
//
//    @Test
//    @DisplayName("Creating a team")
//    void create_team() {
//        //given
//        TeamRequest teamRequest = new TeamRequest("Dream team","Car","Lisbon");
//        //when
//        TeamResponse teamResponse = teamService.createTeam(teamRequest);
//        //then
//        Assertions.assertThat(teamResponse)
//                .as("Response returned with no null values")
//                .hasNoNullFieldsOrProperties()
//                .usingRecursiveComparison()
//                .ignoringFields("id","memberList")
//                .as("Response was returned with the data provided")
//                .isEqualTo(teamRequest);
//
//        Assertions.assertThat(teamRepository.findById(teamResponse.getId())).isNotNull();
//    }
//}