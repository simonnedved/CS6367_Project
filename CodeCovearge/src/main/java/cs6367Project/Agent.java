package cs6367Project;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassTransformer("org/apache/commons/dbutils/"));
    }
}