package com.ctw.workstation.team.boundary;

import com.ctw.workstation.config.CtwAcademyResource;
import com.ctw.workstation.config.CtwAcademyTestProfile;
import com.ctw.workstation.data.TeamRepository;
import com.ctw.workstation.services.TeamService;
import com.ctw.workstation.team.dto.TeamRequest;
import com.ctw.workstation.team.dto.TeamResponse;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.dto.TeamMemberResponse;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

//@QuarkusTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
////@QuarkusTestResource(CtwAcademyResource.class)
////@TestProfile(CtwAcademyTestProfile.class)
////@TestHTTPEndpoint(TeamResource.class)
//class TeamResourceTest {
//    @Inject
//    TeamRepository teamRepository;
//
//    private static Long testTeamId;
//
//
//    @BeforeAll
//    void setUpTestData() {
//        Team team = new Team.Builder().setProduct("Test Product").setName("Test Team").setDefaultLocation("Location").build();
//
//        teamRepository.persistAndFlush(team);
//        testTeamId = team.getId();
//    }
//
//    @AfterAll
//    void tearDownTestData() {
//        Team team = teamRepository.findById(testTeamId);
//        if (team != null) {
//            teamRepository.delete(team);
//        }
//    }
//
//    @Test
//    @DisplayName("Fetching all teams")
//    void fetchAllTeams() {
//        Team team = teamRepository.findById(testTeamId);
//        given().header("Content-Type", "application/json")
//                .when().get("/workstation/teams")
//                .then().statusCode(200)
//                .body("$.size()", equalTo(1))
//                .body("[0].name", equalTo(team.getName()))
//                .body("[0].defaultLocation", equalTo(team.getDefaultLocation()))
//                .body("[0].product", equalTo(team.getProduct()));
//    }
//
//    @Test
//    @DisplayName("Fetching a specific team")
//    void fetchSpecificTeam() {
//        Team team = teamRepository.findById(testTeamId);
//        given().header("Content-Type", "application/json")
//                .when().get("/workstation/teams/{id}", testTeamId)
//                .then().statusCode(200)
//                .body("name", equalTo(team.getName()))
//                .body("product", equalTo(team.getProduct()))
//                .body("defaultLocation", equalTo(team.getDefaultLocation()));
//
//        given().header("Content-Type", "application/json")
//                .when().get()
//                .then().statusCode(404);
//    }
//
//    @Test
//    @DisplayName("Creating a team")
//    void createTeam() {
//        TeamRequest teamRequest = new TeamRequest("Default Team", "Product", "Location");
//        TeamResponse teamResponse = new TeamResponse(null,"Default Team", "Location", "Product", null);
//        TeamResponse response = given()
//                .contentType(ContentType.JSON)
//                .body(teamRequest)
//                .when()
//                .post("/workstation/teams")
//                .then()
//                .assertThat()
//                .statusCode(RestResponse.StatusCode.CREATED)
//                .extract().body().as(TeamResponse.class);
//
//        assertThat(response)
//                .as("Team was created")
//                .hasNoNullFieldsOrProperties()
//                .usingRecursiveComparison()
//                .ignoringFields("id", "memberList")
//                .isEqualTo(teamResponse);
//    }
//
//    @Test
//    @DisplayName("Creating a team with blank values")
//    void createTeamWithBlankValues() {
//        TeamRequest teamRequest = new TeamRequest("", "", "");
//        //TODO Criar class de excepção e mapear as respostas de erro para lá.
//        given()
//                .contentType(ContentType.JSON)
//                .body(teamRequest)
//                .when()
//                .post("/workstation/teams")
//                .then()
//                .statusCode(RestResponse.StatusCode.BAD_REQUEST)
//                .body("title", equalTo("Constraint Violation"))
//                .body("violations[0].field", equalTo("create.teamRequest.product"))
//                .body("violations[0].message", equalTo("must not be blank"))
//                .body("violations[1].field", equalTo("create.teamRequest.name"))
//                .body("violations[1].message", equalTo("must not be blank"));
//    }
//}