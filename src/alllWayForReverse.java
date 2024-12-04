import java.util.LinkedList;
import java.util.Stack;

public class alllWayForReverse {
    public static void main(String[] args){

        var a=checkVowelPresent("ujjawal");
        System.out.println(a);
    }
    public static String RreverseString(String a,int number,String result){
        if(number>=a.length()){

            return result;
        }
        result+=a.charAt((a.length()-1)-number);
        return RreverseString(a,number+=1,result);
    }
    public static String reverseString(String string){
        String result="";
        for(int a=string.length()-1;a>=0;a--){
            result+=string.charAt(a);
        }
        return result;
    }
    static String reverse="";
    public static void reverseStringWithStack(Stack<Character> store,String string,int number,String result){
        if(number>=string.length()) return;
            store.push(string.charAt(number));
            reverseStringWithStack(store,string,number+=1,result);
            reverse+=store.pop();
    }
    public static void swap2Numbers(int a,int b){
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println(a+" = "+b);
    }
    public static boolean checkVowelPresent(String a){
        return a.toLowerCase().matches(".*[aeiou].*");
    }

}
