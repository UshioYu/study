package com.ushio.test.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * @author: ushio
 * @description:
 **/
public class DemoTest {

    @ParameterizedTest
    @MethodSource()
    void testFromJson(User user){
        assertTrue(user.getName().length() > 3);
    }

    public static List<User> testFromJson() throws IOException {
        ObjectMapper objectMapper  = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
        return objectMapper.readValue(DemoTest.class.getResourceAsStream("/1.json"),typeReference);
    }

    @TestFactory
    Collection<DynamicTest> DynamicTestCollection() throws IOException{
        List<DynamicTest> lists = new ArrayList<>();
        ObjectMapper objectMapper  = new ObjectMapper(new YAMLFactory());
        TypeReference<ResultList> typeReference = new TypeReference<ResultList>() {};
        ResultList resultList = objectMapper.readValue(DemoTest.class.getResourceAsStream("/1.yaml"),typeReference);
        //ResultList resultList = objectMapper.readValue(new File("src/test/resources/1.yaml"),typeReference);
        List<Result> results = resultList.getResultList();
        for (Result result:results) {
            System.out.println(result.getName());
            lists.add(DynamicTest.dynamicTest(result.getName(),()->assertTrue(result.isResult())));
        }
        return lists;
    }

    @Test
    void testFromYmal() throws IOException {
        ObjectMapper objectMapper  = new ObjectMapper(new YAMLFactory());
        TypeReference<ResultList> typeReference = new TypeReference<ResultList>() {};
        //ResultList resultList = objectMapper.readValue(new File("src/test/resources/1.yaml"),typeReference);
        ResultList resultList = objectMapper.readValue(DemoTest.class.getResourceAsStream("/1.yaml"),typeReference);
        List<Result> results = resultList.getResultList();
        for (Result result:results) {
            assertTrue(result.isResult());
        }
    }

    @Test
    void lamdaTest(){
        assertAll("开始软断言啦",
                () -> {
                    assertEquals("1","1");
                    System.out.println("aaa");
                    },
                () -> assertEquals("2","1"),
                () -> assertEquals("3","4"));

        List<Executable> executables = new ArrayList<>();
        executables.add(() -> {
            assertEquals("1","1");
            System.out.println("aaa");
        });
        executables.add(() -> assertEquals("2","1"));
        executables.add(() -> assertEquals("3","4"));
        assertAll("开始软断言啦",executables.stream());

        assertAll("head", () -> assertEquals("1","1"), () -> {
            assertEquals("1","1");
            System.out.println("aaa");
            System.out.println("vva");
        });
    }

    @Test
    void getEnvTest(){
        System.out.println(System.getenv());
    }



}
