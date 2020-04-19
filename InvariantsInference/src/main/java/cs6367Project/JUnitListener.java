package cs6367Project;

import org.junit.platform.launcher.TestPlan;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;

import java.io.*;
import java.util.HashSet;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;


public class JUnitListener extends RunListener implements TestExecutionListener{

    @Override
    public void testRunStarted(Description description) throws Exception {
        System.out.println("test run started!!!");
        super.testRunStarted(description);
        VariableCollection.localSet=new HashSet<String>();
        VariableCollection.fieldSet=new HashSet<String>();
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
//        String dir ="target"+File.separator+"6367TraceLog";
//        String localLogPath = dir + File.separator + "localVariables.txt";
//        writeFile(dir,localLogPath,VariableCollection.localSet);
//        String fieldLogPath = dir + File.separator + "fieldVariables.txt";
//        writeFile(dir,fieldLogPath,VariableCollection.fieldSet);
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
//        String dir ="target"+File.separator+"6367TraceLog";
//        String localLogPath = dir + File.separator + "localVariables.txt";
//        writeFile(dir,localLogPath,VariableCollection.localSet);
//        String fieldLogPath = dir + File.separator + "fieldVariables.txt";
//        writeFile(dir,fieldLogPath,VariableCollection.fieldSet);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        System.out.println("test run finished!!!");
        super.testRunFinished(result);
        String dir ="target"+File.separator+"6367TraceLog";
        String localLogPath = dir + File.separator + "localVariables.txt";
        writeFile(dir,localLogPath,VariableCollection.localSet);
        String fieldLogPath = dir + File.separator + "fieldVariables.txt";
        writeFile(dir,fieldLogPath,VariableCollection.fieldSet);

    }


    public void writeFile(String dir, String path, HashSet<String> set){
        try {
            File directory = new File(dir);
            if (! directory.exists()){
                directory.mkdir();
            }
            File file = new File(path);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for(String n :VariableCollection.fieldSet){
                bw.write(n);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
