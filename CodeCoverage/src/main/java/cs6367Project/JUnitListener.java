package cs6367Project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.Failure;

import org.junit.platform.commons.meta.API;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestExecutionResult.Status;
import org.junit.platform.engine.reporting.ReportEntry;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.platform.launcher;


public class JUnitListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        CoverageCollection.testSuite = new HashMap<String, HashMap<String, LinkedHashSet<Integer>>>();
    }
    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) throws Exception{
        super.testPlanExecutionStarted(testPlan);
        CoverageCollection.testSuite = new HashMap<String, HashMap<String, LinkedHashSet<Integer>>>();
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) throws Exception{
        super.testRunFinished(testPlan);
        String dir = "coverageLog";
        String logPath = dir + File.separator + "stmt-cov.txt";
        try {
            File directory = new File(dir);
            if (! directory.exists()){
                directory.mkdir();
            }

            File file = new File(logPath);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuilder sb = new StringBuilder();
            for (String testName : CoverageCollection.testSuite.keySet()) {
                sb.append(testName + "\n");
                HashMap<String, LinkedHashSet<Integer>> testCase = CoverageCollection.testSuite.get(testName);

                for (String className : testCase.keySet()) {
                    for(int i : testCase.get(className)){
                        sb.append(className + ":" + i + "\n");
                    }
                }
            }
            bw.write(sb.toString());
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        String dir = "coverageLog";
        String logPath = dir + File.separator + "stmt-cov.txt";
        try {
            File directory = new File(dir);
            if (! directory.exists()){
                directory.mkdir();
            }

            File file = new File(logPath);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuilder sb = new StringBuilder();
            for (String testName : CoverageCollection.testSuite.keySet()) {
                sb.append(testName + "\n");
                HashMap<String, LinkedHashSet<Integer>> testCase = CoverageCollection.testSuite.get(testName);

                for (String className : testCase.keySet()) {
                    for(int i : testCase.get(className)){
                        sb.append(className + ":" + i + "\n");
                    }
                }
            }
            bw.write(sb.toString());
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        CoverageCollection.testName = "[TEST] " + description.getClassName() + ":" + description.getMethodName();
        CoverageCollection.testCase = new HashMap<String, LinkedHashSet<Integer>>();
        CoverageCollection.testSuite.put(CoverageCollection.testName,CoverageCollection.testCase);
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) throws Exception{
        super.testStarted(testIdentifier);
        CoverageCollection.testName = "[TEST] " + testIdentifier.getDisplayName() + ":" + testIdentifier.getUniqueId();
        CoverageCollection.testCase = new HashMap<String, LinkedHashSet<Integer>>();
        CoverageCollection.testSuite.put(CoverageCollection.testName,CoverageCollection.testCase);
    }
    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) throws Exception{
        super.executionFinished(testIdentifier,testExecutionResult);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) throws Exception{
        super.executionSkipped(testIdentifier,reason);
    }


    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);
    }

}
