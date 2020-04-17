package cs6367Project;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class VariableCollection {
    public static HashMap<String, HashMap<String, LinkedHashSet<Integer>>> testSuite;
    public static HashMap<String, LinkedHashSet<Integer>> testCase;
    public static String testName;

    public static void visitFieldVariable(String className, String methodName, String variableName, String variableType , String variableValue){
//        System.out.println("Class name is " +className);
//        System.out.println("Method name is " +methodName);
//        System.out.println("Field variable name is " +variableName);
//        System.out.println("Field variable type is " +variableType);
//        System.out.println("Field variable value is " +variableValue);
    }

    public static void visitLocalVariable(String className, String methodName, String variableName, String variableType, String variableValue){
//        System.out.println("Class name is " +className);
//        System.out.println("Method name is " +methodName);
//        System.out.println("Local variable name is " +variableName);
//        System.out.println("Local variable type is " +variableType);
//        System.out.println("Local variable value is " +variableValue);
    }
}
