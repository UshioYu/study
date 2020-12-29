package com.ushio.wechat.yaml.model;

import com.ushio.wechat.helper.LogHelper;
import com.ushio.wechat.util.RandomUtil;
import com.ushio.wechat.yaml.result.StepResult;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author: ushio
 * @description:
 **/
public class ApiTestCaseModel {

    private String name;
    private String description;
    private ArrayList<StepModel> steps;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    public void run(){
        ArrayList<Executable> assertList =  new ArrayList<>();
        HashMap<String,String> testCaseVariables = new HashMap<>();
        //加载用例层关键字变量
        testCaseVariables.put("getTimeStamp", RandomUtil.getTimeStamp());
        LogHelper.info("用例变量更新： "+testCaseVariables);
        //遍历steps进行执行
        if(steps!=null){
            steps.forEach(step ->{
                StepResult stepResult =  step.run(testCaseVariables);
                //处理step返回的save变量，这步感觉没啥用
//                if(stepResult.getStepVariables().size()>0){
//                    testCaseVariables.putAll(stepResult.getStepVariables());
//                    LogHelper.info("testcase变量更新： "+ testCaseVariables);
//                }
                //处理assertList,并进行统一断言
                if (stepResult.getAssertList().size() > 0) {
                    assertList.addAll(stepResult.getAssertList());
                }
            });
            //5、进行统一断言
            assertAll("",assertList.stream());
        }
    }

    @Override
    public String toString() {
        return "ApiTestCaseModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", steps=" + steps +
                '}';
    }

}
