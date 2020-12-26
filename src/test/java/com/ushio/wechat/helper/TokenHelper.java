package com.ushio.wechat.helper;

import com.ushio.wechat.util.Constant;

import static io.restassured.RestAssured.given;

public class TokenHelper {

    public static String getAccessToken() {
        //todo getToken放入方法注解的Beforeall好还是通过静态方法去直接获取
        String accessToken = given()
//                    .log().all()
                .when()
                .param("corpid", Constant.CORPID)
                .param("corpsecret", Constant.CORPSECRET)
                .get(Constant.GET_TOKEN_URL)
                .then()
//                    .log().body()
                .extract()
                .response()
                .path("access_token");
        LogHelper.info("access_token: " + accessToken);
        return accessToken;
    }
}
