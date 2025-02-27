package com.ctw.workstation.simple;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HelloExtAcademyTest {

    @Mock
    ExternalMessageService externalMessageService;

    @InjectMocks
    HelloExtAcademyApi helloExtAcademy;

    @Test
    @DisplayName("Say hello with annotation usage")
    void sayHelloWithAnnotationUsage() {
        Mockito.doReturn("Hello World").when(externalMessageService);

        Mockito.when(externalMessageService.sayHelloFromOuterSpace())
                .thenThrow(new NotImplementedException("Not implemented"));
    }

    @Test
    @DisplayName("Say hello from outer space with mock")
    public void sayHelloFromOuterSpaceWithMock() {
// given
        String name = "Ana";
        HelloExtAcademyApi helloExtAcademy = new HelloExtAcademyApi();
        ExternalMessageService externalMessageServiceMock = Mockito.mock(ExternalMessageService.class);
//        helloExtAcademy.externalMessageService = externalMessageServiceMock;

        Mockito.when(externalMessageServiceMock.sayHelloFromOuterSpace())
                .thenThrow(new NotImplementedException("Not implemented"));
        Mockito.when(externalMessageServiceMock.sayHelloFromOuterSpace(Mockito.anyString()))
                .thenReturn("Hello %s from outer space".formatted(name));
// when
        String result = helloExtAcademy.sayHello(name);
// then
        assertEquals("Hello Ana from outer space", result);
    }
    @Test
    @DisplayName("Say hello from outer space with spy")
    public void sayHelloFromOuterSpaceWithSpy() {
// given
        String name = "Ana";
        HelloExtAcademyApi helloExtAcademy = new HelloExtAcademyApi();

        ExternalMessageService externalMessageService = new ExternalMessageServiceImplementation();
        ExternalMessageService externalMessageServiceSpy = Mockito.spy(externalMessageService);
//        helloExtAcademy.externalMessageService = externalMessageServiceSpy;

        Mockito.doReturn("Hello World").when(externalMessageServiceSpy).sayHelloFromOuterSpace();

// when
        String result = helloExtAcademy.sayHello(name);
// then
        assertThat(result).isEqualTo("Hello World");
    }

    @Test
    void lista(){
        List<String> lista = new ArrayList<>();
        List<String> minhaListSpy = Mockito.spy(lista);

        Mockito.doReturn("Rennan").when(minhaListSpy).get(0);
        assertThat(minhaListSpy.get(0)).isEqualTo("Rennan");
    }
}