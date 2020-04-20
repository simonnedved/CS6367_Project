package cs6367Project;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.FieldNode;

import java.util.List;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

    private String methodName;
    private String[] variableNames;
    private String className;
    private String description;
    private FieldNode[] fields;
    private int access;

    public MethodTransformVisitor(final MethodVisitor mv, String className, String name, String desc, int access, String[] variableNames, List<FieldNode> fields) {
        super(Opcodes.ASM5, mv);
        this.methodName = name;
        this.className = className;
        this.variableNames=variableNames;
        this.description=desc;
        this.access=access;
        if (fields != null) {
            this.fields = new FieldNode[fields.size()];
            this.fields = fields.toArray(this.fields);
        }
    }

    @Override
    public void visitCode() {
        if (fields != null) {
            for (FieldNode field : fields) {
                if ((access & Opcodes.ACC_STATIC) != 0 || methodName.equals("<init>")) return;
                boolean isStaticField = (field.access & Opcodes.ACC_STATIC) != 0;
                record(field.desc,field.name,0,"visitFieldVariable", 0, isStaticField);
            }
        }

        Type[] localVariableTypes = Type.getArgumentTypes(description);

        int offset =  (this.access & Opcodes.ACC_STATIC) != 0? 0 : 1;

        String variableName;
        if(variableNames!=null){
            for (int i = 0; i < localVariableTypes.length; i++) {
                variableName = variableNames != null ? variableNames[i + offset] : "variableName";
                record(localVariableTypes[i].getDescriptor(),variableName,i+offset,"visitLocalVariable", 1,true);
            }
        }

        super.visitCode();
    }


    public void record(String desc,String varName,int index,  String funcName, int flag,boolean check){
        int opcode;
        String varType;
        String strType = "Ljava/lang/String;";

        if ("Z".equals(desc)) {
            opcode = Opcodes.ILOAD;
            varType = "boolean";
        } else if ("C".equals(desc)) {
            opcode = Opcodes.ILOAD;
            varType = "char";
//        } else if ("B".equals(desc)) {
//            opcode = Opcodes.ILOAD;
//            varType = "byte";
//            desc="I";
//        } else if ("S".equals(desc)) {
//            opcode = Opcodes.ILOAD;
//            varType = "short";
//            desc="I";
        } else if ("I".equals(desc)) {
            opcode = Opcodes.ILOAD;
            varType = "int";
        } else if ("F".equals(desc)) {
            opcode = Opcodes.FLOAD;
            varType = "float";
        } else if ("J".equals(desc)) {
            opcode = Opcodes.LLOAD;
            varType = "long";
        } else if ("D".equals(desc)) {
            opcode = Opcodes.DLOAD;
            varType = "double";
        } else {
            return;
        }

        mv.visitLdcInsn(className);
        mv.visitLdcInsn(methodName);
        mv.visitLdcInsn(varName);
        mv.visitLdcInsn(varType);

        if(flag==1) {
            mv.visitVarInsn(opcode, index);
        }
        else if(flag==0){
            if (check) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, className, varName, desc);
            }
            else {
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitFieldInsn(Opcodes.GETFIELD, className, varName, desc);
            }
        }

        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/String", "valueOf", "(" + desc + ")" + strType, false);
        mv.visitMethodInsn(INVOKESTATIC, "cs6367Project/VariableCollection", funcName, "("
                + strType
                + strType
                + strType
                + strType
                + strType
                + ")V", false);
    }
}

