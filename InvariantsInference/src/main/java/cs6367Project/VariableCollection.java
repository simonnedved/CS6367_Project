package cs6367Project;

import java.io.*;
import java.util.*;


public class VariableCollection {
    public static HashSet<String> fieldSet;
    public static HashSet<String> localSet;


    public static void visitFieldVariable(String className, String methodName, String variableName, String variableType , String variableValue){
        if(className==null||methodName==null||variableName==null||variableType==null) return;
        {
            if(fieldSet==null){
                fieldSet = new HashSet<>();
            }
            String str = saveToString(className,methodName,variableName,variableType,variableValue);
            fieldSet.add(str);
        }

    }

    public static void visitLocalVariable(String className, String methodName, String variableName, String variableType, String variableValue){
        if(className==null||methodName==null||variableName==null||variableType==null) return ;
        {
            if(localSet==null){
                localSet = new HashSet<>();
            }
            String str = saveToString(className,methodName,variableName,variableType,variableValue);
            localSet.add(str);
        }
    }


    public static String saveToString(String className, String methodName, String variableName, String variableType, String variableValue){

        return className.replaceAll(File.separator,".") + File.separator +
                    methodName + File.separator +
                    variableName + File.separator +
                    variableType + File.separator +
                    variableValue+"\n";
    }

}

