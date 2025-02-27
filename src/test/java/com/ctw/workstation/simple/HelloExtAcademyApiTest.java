package com.ctw.workstation.simple;

import com.ctw.workstation.config.CtwAcademyTestProfile;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(CtwAcademyTestProfile.class)
class HelloExtAcademyApiTest {

    @Inject
    HelloExtAcademyApi helloExtAcademy;

    @Test
    @DisplayName("Saying hello to external api")
    void saying_hello_to_external_api() {
        //given
        String name = "Ana";
        //when
        String result = helloExtAcademy.sayHello(name);
        //then
        assertThat(result).isEqualTo("Hello Ana!");
    }
}