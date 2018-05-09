package com.sun.test.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b><code>TestController</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2018/5/9 15:55
 *
 * @author Sun Jinpeng
 * @version 0.1.0
 * @since demo 0.1.0
 */
@RestController
@RequestMapping("/v1.0")
public class TestController {


    /**
     * The Env.
     */
    @Value("${spring.datasource.url}")
    private String env;

    /**
     * Test response entity.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/test",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> test(){
        return new ResponseEntity<>(env, HttpStatus.OK);
    }
}
