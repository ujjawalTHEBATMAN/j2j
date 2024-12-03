import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
//        long time=System.currentTimeMillis();
/*
for(int a=8000;a<=8020;a++) {
    System.out.println(a +" = " + SfiboniccSerieas(0, 1+a, "0", "1"));
    ;
}


        System.out.println(System.currentTimeMillis()-time);
*/}


    public static long fiboniccSerieas(int number){ //    90 - 99
        // step one with loop
         long a=0;
        long b=1;
        for(int aa=1;aa<number;aa++){
            long temp=b;
            b=a+b;
            a=temp;
        }
        return b;
    }
    public static long RfiboniccSerieas(int number,int before,int after){ //    90 - 99
        if(number<=1){
            return after;
        }
        int temp=after;
        after=before+after;
        before=temp;
        return RfiboniccSerieas(--number,before,after);
    }


    public static String SfiboniccSerieas(int n,int last,String before,String after){ // 8100 - 8200
        if(n>=last){
            return after;
        }
        String temp=after;
        after=SAddition(0,before,after,"","0");
        before=temp;
        return SfiboniccSerieas(n+=1,last,before,after);
    }


    public static String SAddition(int n,String a,String b,String result,String du){
        //0,random,random,0,0
        if(n>=(a.length()-1)&&n>=(b.length()-1)){
            if(a.length()==b.length()){
            String temp=SAdding(""+a.charAt(0),""+b.charAt(0),du);
            int count=temp.length()-1;

            while(count>=0){
                result+=temp.charAt(count);
                count-=1;
            }
            }
            else{
                String temp=null;
                if(a.length()<b.length()) {
                    temp=SAdding(""+b.charAt(0),"0",du);
                    if(temp.length()>1){
                        int t=temp.length()-1;
                        while(t>=0){
                            result+=temp.charAt(t);
                            t-=1;
                        }
                    }
                    else {
                        result+=temp;
                    }
                }
                else{ result+=a.charAt(0);}

            }
            result=reverse(result,"",0);
            return result;
        }
        // 188989880111
        //   8989880111
        //char temp1=(a.length()>=n)?a.charAt((a.length()-1)-n):'0';
        //char temp2=(b.length()>=n)?b.charAt((b.length()-1)-n):'0';
        char temp1=(n>=a.length())?'0':a.charAt((a.length()-1)-n);
        char temp2=(n>=b.length())?'0':b.charAt((b.length()-1)-n);


        String answer=SAdding(""+temp1,""+temp2,du);
        String r=result;
        String d="0";
        if(answer.length()>1){
            r+=answer.charAt(answer.length()-1);
            d=answer.substring(0,answer.length()-1);
        }
        else{
            r+=answer;
        }
        return SAddition(n+=1,a,b,r,d);
    }
    public static String SAdding(String a, String b,String d) {
        return String.valueOf(Integer.parseInt(a)+Integer.parseInt(b)+Integer.parseInt(d));
    }
public static String reverse(String number,String result,long no){
        if(no>=number.length()){
            return result;
        }
        result+=number.charAt((int) ((number.length()-1)-no));
        return reverse(number, result, no+=1);
    }



   public static BigInteger BIfibonacciSeries(int number, BigInteger a,BigInteger b){ // 16000 - 16600 or infinity (ha ha ha )
        if(number <= 1){
            return b;
        }
        BigInteger temp=b;
        b=a.add(b);
        a=temp;

        return BIfibonacciSeries(number-=1,a,b);
   }


}