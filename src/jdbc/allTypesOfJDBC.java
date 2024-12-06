package jdbc;

import java.sql.*;
import java.util.Scanner;

public class allTypesOfJDBC {

    public static void main(String[] args) throws Exception {
        ConnectionData con=new ConnectionData();
        con.setDatabase("javaProgram");
        con.setUrl("jdbc:mysql://localhost:3306");
        con.setPassword("root");
        con.setUser("root");
        Connection cona=stayConnected(con);

        System.out.println(GinsertDummyData(cona,"program1",1,10000));;


    }

    public static void displayTables(ConnectionData c)throws Exception {
        Connection con=stayConnected(c);
        ResultSet rs=con.createStatement().executeQuery("show tables");
        while(rs.next()){
            for(int a=1;a<=rs.getMetaData().getColumnCount();a++){
                System.out.print(rs.getString(a));
            }
            System.out.println();
        }
    }
    public static void displayTableData(ConnectionData c,String tableName)throws Exception{
        Connection con =stayConnected(c);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from "+tableName+" ");

        while(rs.next()){
            for(int a=1;a<=rs.getMetaData().getColumnCount();a++){
                System.out.print(rs.getString(a)+" ");
            }
            System.out.println();
        }
    }
public static int  createDatabase(ConnectionData cd,String databaseName) throws Exception {
        Connection con=stayConnected(cd);
        int temp=con.createStatement().executeUpdate("create database "+databaseName+"");
        return temp;
}
    public static void createTable(ConnectionData c,String tableName,String[] desc) throws Exception {
            Connection con=stayConnected(c);
            String description="";
            int count=0;
            for(String d: desc){
                count++;
                if(count!=(desc.length)) {
                    description += (d + ",");
                }
                else{
                description+=d;
                }
        }
            con.createStatement().executeUpdate("create table "+tableName+" ("+description+")");
    }
    public static String insertData(ConnectionData c,String tablename)throws Exception{
       Connection con=stayConnected(c);
        int temp=con.createStatement().executeQuery("select * from "+tablename+"").getMetaData().getColumnCount();

        String datas="";
        for(int a=1;a<=temp;a++){
            String tem=con.createStatement().executeQuery("select * from "+tablename+"").getMetaData().getColumnName(a);
            System.out.print("enter "+tem+" column values = ");
            if(a!=temp)
            {
            datas+="'"+(new Scanner(System.in).next())+"',";
            }
            else{
                datas+="'"+(new Scanner(System.in).next())+"'";
            }

        }
        int inserted=con.createStatement().executeUpdate("insert into "+tablename+" values("+datas+")");

        return (inserted==1)?"data inserted":"data not inserted";

    }
    public static String updateData(ConnectionData c,String tablename)throws Exception{
        Connection con=stayConnected(c);
        int temp=con.createStatement().executeQuery("select * from "+tablename+"").getMetaData().getColumnCount();
        System.out.println("just number from the given column \n  for update the column");
        for(int a=1;a<=temp;a++){
            String tem=con.createStatement().executeQuery("select * from "+tablename+"").getMetaData().getColumnName(a);
            System.out.println(a+" = "+tem+" ");
        }
        int in1=new Scanner(System.in).nextInt();
        System.out.println("enter where cause to update the table \n first enter the changing values \n then enter the where cause values ");

       int result= con.createStatement().executeUpdate("update "+tablename+" set "+(con.createStatement().executeQuery("select * from "+tablename+" ").getMetaData().getColumnName(in1))+" = '"+(new Scanner(System.in).nextLine())+"' where "+((in1==1)?con.createStatement().executeQuery("select * from "+tablename+" ").getMetaData().getColumnName(2):con.createStatement().executeQuery("select * from "+tablename+" ").getMetaData().getColumnName(1))+" = '"+(new Scanner(System.in).nextLine())+"' ");

        return (result==0)?"data not updated":"data updated";

    }
    public static String deleteData(ConnectionData c,String tablename)throws Exception {
        Connection con=stayConnected(c);
        System.out.println("enter the id which you need to test  deleting table data = ");

        int tmep=con.createStatement().executeUpdate("delete from "+tablename+" where id = "+(new Scanner(System.in).nextLine())+"");
        return (tmep==0)?tmep+"data not deleted from the table":tmep+" data deleted from the tables  ";
    }

    public static String GinsertDummyData(Connection con,String tablename,int cound,int max)throws Exception{
        System.gc();
        if(cound>=max) return "data inserted to the database";

        int temp=con.createStatement().executeQuery("select * from "+tablename+"").getMetaData().getColumnCount();

        String datas="";
        for(int a=1;a<=temp;a++){
            String tem=con.createStatement().executeQuery("select * from "+tablename+"").getMetaData().getColumnName(a);

            if(a!=temp)
            {
                datas+="'"+GcreateRandomString(10)+"',";
            }
            else{
                datas+="'"+GcreateRandomString(10)+"'";
            }

        }
        int inserted=con.createStatement().executeUpdate("insert into "+tablename+" values("+datas+")");

        return GinsertDummyData(con,tablename,cound+=1,max);
    }
    public static String GcreateRandomString(int size){
       String result="";
        for(int a=0;a<size;a++){
            result+=(char)(GrandomForText());
        }
        return makePreetierText(result);
    }

    public static String GScreateRandomid(){
        return ""+Math.round(Math.random()*10000);
    }
    public static int GIcreateRandomid(){
        return (int)Math.round(Math.random()*10000);
    }
    public static int GrandomForText(){
        //65-90   97-122
        int temp=(int) Math.round(Math.random()*123);
        return (temp>=65&&temp<=90||temp>=97&&temp<=122)?temp:GrandomForText();
    }
    public static String makePreetierText(String str){
        return (str == null || str.isEmpty())?"":(""+str.charAt(0)).toUpperCase()+""+str.substring(1,str.length()).toLowerCase();
    }
    public static Connection stayConnected(ConnectionData con)throws Exception{
        return
                DriverManager.getConnection(""+con.getUrl()+"/"+con.getDatabase()+"",""+con.getUser()+"", ""+con.getPassword()+"");
    }
}

