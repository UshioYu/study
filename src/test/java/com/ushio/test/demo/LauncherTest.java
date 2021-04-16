package com.ushio.test.demo;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.ClassNameFilter.excludeClassNamePatterns;
import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.*;

/**
 * @author: ushio
 * @description:
 **/
public class LauncherTest {

    public static void main(String[] args) {
        //类似testNG的xml形式
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage(""),
                        selectClass(DemoTest.class),
                        selectMethod("")
                )
                .filters(
                        includeClassNamePatterns(".*ATest"),
                        excludeClassNamePatterns("")
                )
                .build();
        Launcher launcher  = LauncherFactory.create();
        TestExecutionListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }
}
