package cs6367Project;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.util.List;
import java.io.IOException;


class ClassTransformVisitor extends ClassVisitor implements Opcodes {

    private String className;
    private ClassNode classNode;
    private List<FieldNode> fieldNodes;

    @SuppressWarnings("unchecked")
    public ClassTransformVisitor(final ClassVisitor cv, final String className) {
        super(Opcodes.ASM5, cv);
        this.className = className;
        this.classNode = new ClassNode();

        try {
            ClassReader reader = new ClassReader(className);
            reader.accept(classNode, 0);
            this.fieldNodes = classNode.fields;
        } catch(IOException ignored) {
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        String[] variableNames = null;
        List<MethodNode> methodNodes = classNode.methods;

        for (MethodNode methodNode : methodNodes) {
            if (methodNode.name.equals(name)) {
                variableNames = new String[methodNode.localVariables.size()];
                List<LocalVariableNode> variableNodes = methodNode.localVariables;
                for (LocalVariableNode variableNode : variableNodes) {
                    variableNames[variableNode.index] = variableNode.name;
                }
            }
        }

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new MethodTransformVisitor(mv, className,name,desc,access,variableNames, fieldNodes);
    }


}