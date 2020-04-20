package cs6367Project;

import java.io.*;
import java.util.*;

public class InvariantsInferring{

    private HashSet<String> theSet;
    public static HashMap<String,HashSet<String>> theMap = new HashMap<String, HashSet<String>>();

    public InvariantsInferring(HashSet<String> theSet) {
        this.theSet=theSet;
    }


    public static void saveToFile(String className, String methodName, String variableName, String variableType, String variableValue){
        String sb= className.replaceAll(File.separator,".") + File.separator +
                methodName + File.separator +
                variableName + File.separator +
                variableType;

        HashSet<String> set = theMap.get(sb);
        if(set==null){
            set= new HashSet<String>();
        }
        set.add(variableValue.substring(0,variableValue.length()-1));
        theMap.put(sb,set);
    }


    public void start() {

        List<String>  theList = new ArrayList<String>(theSet);
        Collections.sort(theList);
        for(String n :theList){
            String[] str = n.split(File.separator);
            saveToFile(str[0],str[1],str[2],str[3],str[4]);
        }

        String dir ="target"+File.separator+"6367InferLog";

        String fieldLogPath = dir + File.separator + "Invariants.txt";
        writeFile(dir,fieldLogPath,theMap);

    }


    public void writeFile(String dir, String path, HashMap<String, HashSet<String>> m){
        try {
            File directory = new File(dir);
            if (! directory.exists()){
            directory.mkdir();
            }
            File file = new File(path);
            if (!file.exists())
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuilder sb = new StringBuilder();
            for(String cName :m.keySet()){
                String[] temp = cName.split(File.separator);

                sb.append("Class Name:").append("\t").append("<").append(temp[0]).append(">").append("\n");
                sb.append("Method Name:").append("\t").append("<").append(temp[1]).append(">").append("\n");
                sb.append("Variable Name:").append("\t").append("<").append(temp[2]).append(">").append("\n");
                sb.append("variable Type:").append("\t").append("<").append(temp[3]).append(">").append("\n");

                sb.append("Invariant Patterns:").append("\n");
                HashSet<String> values = m.get(cName);
                List<String> valueList = new ArrayList<String>(values);
                sort(valueList,temp[3]);

                sb.append("Uninitialized?").append("\t").append("It is initialized!").append("\n");

                sb.append("Constant?").append("\t");
                if(valueList.size()!=1){
                    sb.append("Not a constant value! ").append("\n").append("The value set: ").append("\t");
                }
                else{
                    sb.append("It is a constant value! ").append("\n").append("The value is: ").append("\t");
                }
                for (String v : valueList) {
                    sb.append(v).append(" ");

                }
                sb.append("\n");

                sb.append("Range Limits:").append("\t");
                if(valueList.size()!=1) {
                    sb.append("[").append(valueList.get(0)).append(",").append(valueList.get(valueList.size() - 1)).append("]").append("\n");

                }
                else{
                    sb.append("[").append(valueList.get(0)).append("]").append("\n");
                }

                sb.append("Non-zero?").append("\t");
                if(valueList.contains("0")||valueList.contains("0.0")){
                    sb.append("<").append(temp[2]).append(">").append(" can be ZERO").append("\n");
                }
                else{
                    sb.append("<").append(temp[2]).append(">").append(" will not be ZERO").append("\n");
                }

                sb.append("Modulus?").append("\t");
                boolean flag = false;
                HashSet<Integer> x = new HashSet<Integer>();
                if(!temp[3].equals("int")){
                    sb.append("Non-Modulus!").append("\n");
                }
                else{
                    for(String str:valueList){
                        int n = Integer.parseInt(str);
                        List<Integer> cf = isPrime(n);
                        for(int i:cf){
                            if(i!=-1){
                                x.add(i);
                                flag = true;
                            }
                        }
                    }
                    if(flag){
                        sb.append("Modulus: ");
                        for(int i:x){
                            sb.append("<").append(i).append(">");
                        }
                    }
                    else{
                        sb.append("Non-Modulus!");
                    }
                }

                sb.append("\n");
                sb.append("\n");

            }
            bw.write(sb.toString());
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void sort(List<String> list, String type){
        if(!type.equals("int") && !type.equals("double") && !type.equals("long")&&!type.equals("float")) return;
        Object[] a = new Object[list.size()];
        for(int i=0;i<a.length;i++)
        {
            if(type.equals("int")) a[i]=Integer.parseInt(list.get(i));
            if(type.equals("double")) a[i]=Double.parseDouble(list.get(i));
            if(type.equals("long")) a[i]=Long.parseLong(list.get(i));
            if(type.equals("float")) a[i]=Float.parseFloat(list.get(i));
        }
        Arrays.sort(a);

        list.clear();
        for(int i=0;i<a.length;i++)
        {
            list.add(i,String.valueOf(a[i]));
        }

    }


    public List<Integer> isPrime(int n)
    {
        List<Integer> temp = new LinkedList<Integer>();
        if (n <= 1) temp.add(-1);
        if(n>200){
            temp.add(-1);
        }
        else{
            for (int i = 2; i < n; i++) {
                if (n % i == 0) temp.add(i);
            }
        }
        return temp;
    }

}
