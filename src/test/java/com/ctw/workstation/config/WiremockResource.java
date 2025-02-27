package com.ctw.workstation.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class WiremockResource implements QuarkusTestResourceLifecycleManager {

    WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
        wireMockServer.start();
        wireMockServer.stubFor(get(urlEqualTo("/external/hello")).willReturn(aResponse().withStatus(200)));
        wireMockServer.stubFor(post(urlEqualTo("/external/hello")).willReturn(aResponse()
                .withStatus(200)
                .withBody("Hello Ana!")));
        return Map.of("external-api.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        wireMockServer.stop();
    }
}
