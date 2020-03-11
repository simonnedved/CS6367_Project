package cs6367Project;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CoverageCollection {
    public static HashMap<String, HashMap<String, LinkedHashSet<Integer>>> testSuite;
    public static HashMap<String, LinkedHashSet<Integer>> testCase;
    public static String testName;

    public static void visitLine(String className, int line){
        if(testCase == null || className == null) return;
        LinkedHashSet<Integer> set = testCase.get(className);
        if(set == null) {
            set = new LinkedHashSet<>();
        }
        set.add(line);
        testCase.put(className, set);
    }

}
