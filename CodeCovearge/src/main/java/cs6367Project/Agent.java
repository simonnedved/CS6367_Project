package cs6367Project;


import java.io.File;
import java.lang.instrument.Instrumentation;
import java.util.HashSet;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        //HashSet<String> allPackages = getAllPackages();
        //String packageName = shortestPackage(getAllPackages());
        inst.addTransformer(new ClassTransformer("org/apache/commons/dbutils/"));
    }

//    private static String shortestPackage(HashSet<String> packages) {
//        String result = null;
//        for (String packageName : packages) {
//            if (result == null || packageName.length() < result.length())
//                result = packageName;
//        }
//
//        return result;
//    }
//
//    private static HashSet<String> getAllPackages() {
//        String prefix = "src" + File.separator + "main" + File.separator + "java";
//        File root = new File(prefix);
//        File[] list = root.listFiles();
//        HashSet<String> packages = new HashSet<>();
//        listAllPackages(list, packages, prefix);
//
//        return packages;
//    }
//
//    private static void listAllPackages(File[] files, HashSet<String> packages, String prefix) {
//        for (File file : files) {
//            if (file.isDirectory()) {
//                listAllPackages(file.listFiles(), packages, prefix);
//            } else {
//                String path = file.getParent();
//                path = path.substring(path.lastIndexOf(prefix) + prefix.length() + 1);
//                packages.add(path);
//            }
//        }
//    }


}