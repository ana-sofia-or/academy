package com.ctw.workstation.simple;

import io.quarkus.logging.Log;

public class ExternalMessageServiceImplementation implements ExternalMessageService {

    @Override
    public String sayHelloFromOuterSpace(String name){
        return "Hello from outer space" + name;
    }

    @Override
    public String sayHelloFromOuterSpace(){
        return "Hello from outer space";
    }
}
