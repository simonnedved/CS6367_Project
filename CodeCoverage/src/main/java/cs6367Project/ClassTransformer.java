package cs6367Project;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {

    private final String path;

    public ClassTransformer(String path) {
        this.path = path;
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className.startsWith(path)){
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            ClassTransformVisitor ca = new ClassTransformVisitor(cw, className);
            cr.accept(ca, 0);
            return cw.toByteArray();
        }
        return classfileBuffer;
    }
}
