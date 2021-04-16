package com.ushio.test.demo;

import java.util.List;

/**
 * @author: ushio
 * @description:
 **/
public class ResultList {

    private List<Result> resultList;

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public List<Result> getResultList() {
        return resultList;
    }


    @Override
    public String toString() {
        return "ResultList{" +
                "resultList=" + resultList +
                '}';
    }

}
