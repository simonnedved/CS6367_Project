package cs6367Project;

import java.io.*;
import java.util.*;


public class VariableCollection {
    public static HashSet<String> fieldSet;
    public static HashSet<String> localSet;
    private static String dir ="target"+File.separator+"6367TraceLog";


    public static void visitFieldVariable(String className, String methodName, String variableName, String variableType , String variableValue){
        if(className==null||methodName==null||variableName==null||variableType==null||variableValue==null) return;
        {
            if(fieldSet==null){
                fieldSet = new HashSet<String>();
            }
            String str = saveToString(className,methodName,variableName,variableType,variableValue);
            fieldSet.add(str);

//            String localLogPath = dir + File.separator + "localVariables.txt";
//            writeFile(dir,localLogPath,str);
        }

    }

    public static void visitLocalVariable(String className, String methodName, String variableName, String variableType, String variableValue){
        if(className==null||methodName==null||variableName==null||variableType==null||variableValue==null) return ;
        {
            if(localSet==null){
                localSet = new HashSet<String>();
            }
            String str = saveToString(className,methodName,variableName,variableType,variableValue);
            localSet.add(str);
//            String fieldLogPath = dir + File.separator + "fieldVariables.txt";
//            writeFile(dir,fieldLogPath,str);
        }
    }

    public static String saveToString(String className, String methodName, String variableName, String variableType, String variableValue){

        String sb= className.replaceAll(File.separator,".") + File.separator +
                    methodName + File.separator +
                    variableName + File.separator +
                    variableType + File.separator +
                    variableValue + "\n";

        return sb;
    }


//    public static void writeFile(String dir, String path, String str){
//        try {
//            File directory = new File(dir);
//            if (! directory.exists()){
//                directory.mkdir();
//            }
//            File file = new File(path);
//            if (!file.exists())
//                file.createNewFile();
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
//
//            bw.write(str);
//
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

