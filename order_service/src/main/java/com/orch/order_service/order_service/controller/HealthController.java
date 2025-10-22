package com.orch.order_service.order_service.controller;


import org.springframework.http.ResponseEntity;import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;import org.springframework.web.bind.annotation.RestController;



import java.time.LocalDateTime;import java.time.LocalDateTime;

import java.util.Map;import java.util.Map;



/**/**

 * Health check controller * Health check controller

 */ */

@RestController@RestController

public class HealthController {public class HealthController {



    @GetMapping("/health")    @GetMapping("/health")

    public ResponseEntity<Map<String, String>> health() {    public ResponseEntity<Map<String, String>> health() {

        var healthStatus = Map.of(        var healthStatus = Map.of(

            "status", "UP",            "status", "UP",

            "service", "inventory-service",            "service", "order-service",

            "timestamp", LocalDateTime.now().toString(),            "timestamp", LocalDateTime.now().toString(),

            "version", "0.0.1-SNAPSHOT"            "version", "0.0.1-SNAPSHOT"

        );        );

        return ResponseEntity.ok(healthStatus);        return ResponseEntity.ok(healthStatus);

    }    }

}}

