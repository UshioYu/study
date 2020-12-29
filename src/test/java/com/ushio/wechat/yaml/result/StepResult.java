package com.ushio.wechat.yaml.result;

import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

/**
 * @author: ushio
 * @description:
 **/
public class StepResult {
    private ArrayList<Executable> assertList;
    private Response response;

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
