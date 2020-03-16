package cs6367Project;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.util.HashSet;



class MethodTransformVisitor extends MethodVisitor implements Opcodes {

    String mName;
    int line;
    private HashSet<Integer> visitedLines;
    private String className;

    public MethodTransformVisitor(final MethodVisitor mv, String name, String className) {
        super(Opcodes.ASM5, mv);
        this.mName = name;
        this.className = className;
        visitedLines = new HashSet<Integer>();
    }

    @Override
    public void visitLineNumber(int i, Label label) {
        this.line = i;
        record(i);
        super.visitLineNumber(i, label);
    }

    @Override
    public void visitLabel(Label label) {
        record(line);
        super.visitLabel(label);
    }

    private void record(int line) {
        if (line == 0) return;
        String temp = className;
        mv.visitLdcInsn(temp);
        mv.visitLdcInsn(line);
        mv.visitMethodInsn(INVOKESTATIC, "cs6367Project/CoverageCollection", "visitLine", "(Ljava/lang/String;I)V", false);
    }

}