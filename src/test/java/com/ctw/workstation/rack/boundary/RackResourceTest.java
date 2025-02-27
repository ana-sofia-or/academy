package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.data.RackRepository;
import com.ctw.workstation.data.TeamRepository;
import com.ctw.workstation.rack.dto.RackRequest;
import com.ctw.workstation.rack.dto.RackResponse;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.dto.TeamRequest;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RackResourceTest {
    @Inject
    RackRepository rackRepository;
    @Inject
    TeamRepository teamRepository;

    private static Long testRackId;
    private static Long testTeamId;


    @BeforeAll
    void setUpTestData() {
        Team team = new Team.Builder().setProduct("Test Product").setName("Test Team").setDefaultLocation("Location").build();
        teamRepository = new TeamRepository();
        teamRepository.persistAndFlush(team);

        Rack rack = new Rack.Builder().setSerialNumber("04-123").setTeam(team).setDefaultLocation("Location").build();

        rackRepository.persistAndFlush(rack);
        testRackId = rack.getId();
        testTeamId = team.getId();
    }

    @AfterAll
    void tearDownTestData() {
        Team team = teamRepository.findById(testTeamId);
        if (team != null) {
            teamRepository.delete(team);
        }
        Rack rack = rackRepository.findById(testRackId);
        if (rack != null) {
            rackRepository.delete(rack);
        }
    }

    @Test
    @DisplayName("Fetching all racks")
    void fetchAllRacks() {
        Rack rack = rackRepository.findById(testRackId);
        given().header("Content-Type", "application/json")
                .when().get("/workstation/racks")
                .then().statusCode(200)
                .body("$.size()", equalTo(1))
                .body("[0].serialNumber", equalTo(rack.getSerialNumber()))
                .body("[0].defaultLocation", equalTo(rack.getDefaultLocation()))
                .body("[0].teamId", equalTo(rack.getTeam().getId()));
    }

    @Test
    @DisplayName("Fetching a specific rack")
    void fetchSpecificRack() {
        Rack rack = rackRepository.findById(testRackId);
        given().header("Content-Type", "application/json")
                .when().get("/workstation/teams/{id}", testRackId)
                .then().statusCode(200)
                .body("serialNumber", equalTo(rack.getSerialNumber()))
                .body("teamId", equalTo(rack.getTeam().getId()))
                .body("defaultLocation", equalTo(rack.getDefaultLocation()));

        given().header("Content-Type", "application/json")
                .when().get("/workstation/racks/{id}", 999)
                .then().statusCode(404);
    }

    @Test
    @DisplayName("Creating a team")
    void createRack() {
        RackRequest rackRequest = new RackRequest("Lisbon", "04-563", "AVAILABLE",testTeamId);
        RackResponse rackResponse = new RackResponse("04-563","Lisbon","AVAILABLE", teamRepository.findById(testTeamId).getName());

        RackResponse response = given()
                .contentType(ContentType.JSON)
                .body(rackRequest)
                .when()
                .post("/workstation/teams")
                .then()
                .assertThat()
                .statusCode(RestResponse.StatusCode.CREATED)
                .extract().body().as(RackResponse.class);

        assertThat(response)
                .as("Team was created")
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(rackResponse);

    }
}