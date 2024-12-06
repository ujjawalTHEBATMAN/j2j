package apacheIO;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils.*;
public class pdecFileUtils {
    static Stack<File> storePreviousFile =new Stack<>();
    public static void main(String... args)throws Exception {
        System.out.println();
        System.out.println();
detailsForD(new File("D:\\webDevelopment"));

    }

    public static void detailsForD(File a) throws Exception {
        storePreviousFile.push(a);
        System.out.println("FILE DETAILES");
        // file types
        String types="FILE TYPES = "+((FileUtils.isDirectory(a)==true)?"Directory":"File");
        // file name
       String name= "FILE NAME = "+a.getName();
        // file size
        long pSize=FileUtils.sizeOf(a);
        String size="FILE SIZE = "+((pSize<(1024.0*1024.0))?" "+Math.round(((double)pSize/(1024.0)))+" KB ":(pSize>=(1024.0*1024.0)+1&&pSize<=1024.0*1024.0*1024.0)?Math.round(((double)pSize/(1024.0*1024.0)))+" MB ":Math.round(((double)pSize/(1024.0*1024.0*1024.0)))+" GB");

        // file path
        String path="FILE PATH = "+a.getPath();
        // if(dir)->{
        // show all file present on the directory
        // }
        int features=1;
        System.out.println(name);
        System.out.println(types);
        System.out.println(size);
        System.out.println(path);
        if(true){
            File[] temp= a.listFiles();

            // all the sout function perform here
            System.out.println((features++)+" show total file available ");
            if(storePreviousFile.size()>=2){
            System.out.println((features++)+" > did you want back  ");
            } else if (types.equalsIgnoreCase("FILE TYPES = File")) {
                System.out.println((features++)+"read file");
                System.out.println((features++)+"write file");
            }

            // input part which handle the core concepth of the features

            String input=new Scanner(System.in).next();

            // feature 1
            if((!(temp.length <1))&&types.equals("FILE TYPES = Directory")&&input.equals("1")){
                int count=1;
                for(File te:temp){
                    System.out.println(count+" > "+te);
                    count++;
                }
                System.out.println("choose one out of all option =  ");
                System.out.println("show \n back \n read \n write");

                String scanner=new Scanner(System.in).nextLine();
                if(scanner.equalsIgnoreCase("show")){
                    System.out.println("enter number = ");
                    detailsForD(new File(temp[new Scanner(System.in).nextInt()-1].toString()));
                }
                if(scanner.equalsIgnoreCase("back")){
                    if(storePreviousFile.size()>=1){
                        detailsForD(back(a));
                    }
                }
                if(scanner.equalsIgnoreCase("read")){
                    var dir=storePreviousFile.peek();
                    String textfile[]=dir.list();
                    int c=1;
                    for(String aa:textfile){
                        System.out.println((c++)+" = "+aa);
                    }
                    System.out.println("enter that your want to");
                    int number =new Scanner(System.in).nextInt();
                    File re=new File(storePreviousFile.peek().toString()+"\\"+textfile[number-1]);
                    System.out.println(read(re));

                    detailsForD(storePreviousFile.peek());

                }
                if(scanner.equalsIgnoreCase("write")){
                    System.out.println("1  > New File \n  2  > Choose from current File System");
                    int choose = new Scanner(System.in).nextInt();
                    if(choose==1){
                        System.out.println("enter your new file name");
                        String new_filename=storePreviousFile.peek().toString()+"//"+(new Scanner(System.in).nextLine());
                      FileUtils.touch(new File(new_filename));
                      write(new File(new_filename));

                    }
                    else if(choose==2) {
                        String[] t=storePreviousFile.peek().list();
                        var co=1;
                        for(String t1:t){
                            System.out.println((co++)+""+t1);
                        }
                        System.out.println("enter writable file number = ");
                        int nuim=new Scanner(System.in).nextInt();
                        write(new File(storePreviousFile.peek().toString()+"//"+t[co-1]));
                    }


                }
            }

            // feature 2
            if(input.equalsIgnoreCase("2")){
                    detailsForD(back(a));
            }
            // feature 3 read and write
            if(types.equalsIgnoreCase("FILE TYPES = File")&&input.equalsIgnoreCase("3")){
                read(a);
            }

            if(types.equalsIgnoreCase("FILE TYPES = File")&&input.equalsIgnoreCase("4")){
                write(a);
            }
        }





    }
    public static File back(File f){

            storePreviousFile.pop();

        return storePreviousFile.peek();
    }

    public static String read(File a) throws IOException {
        return FileUtils.readFileToString(a);
    }

    public static boolean write(File a)throws Exception{
        System.out.println("enter :-   ");
            FileUtils.writeStringToFile(a,new Scanner(System.in).nextLine());
        return true;
    }
    public static boolean write(File pastehere ,File copyhere)throws Exception{
        if((!copyhere.canRead())||(! copyhere.exists()) ) return false;
        FileUtils.writeStringToFile(pastehere,FileUtils.readFileToString(copyhere));
    return true;
    }

}









