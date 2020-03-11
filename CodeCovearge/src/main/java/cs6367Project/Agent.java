package cs6367Project;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassTransformer("org/apache/commons/dbutils/"));
        inst.addTransformer(new ClassTransformer("org/joda/time/"));
        inst.addTransformer(new ClassTransformer("org/hashids/"));
        inst.addTransformer(new ClassTransformer("cz/startnet/utils/pgdiff/"));
        inst.addTransformer(new ClassTransformer("brickhouse/"));
        inst.addTransformer(new ClassTransformer("com/jakewharton/byteunits"));
        inst.addTransformer(new ClassTransformer("com/sampullara/cli"));
        inst.addTransformer(new ClassTransformer("eu/danieldk/dictomaton"));
        inst.addTransformer(new ClassTransformer("com/github/vbauer/caesar/"));

        inst.addTransformer(new ClassTransformer("org/trendafilov/confucius"));

        inst.addTransformer(new ClassTransformer("io/gsonfire/"));
    }
}