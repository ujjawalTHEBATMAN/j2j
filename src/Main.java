import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.buffer.PeekableInputStream;
import org.apache.commons.io.input.buffer.PeekableInputStream.*;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
       var a= System.currentTimeMillis();
        System.out.println(  FileUtils.readFileToString(new File("C:\\java enviroment\\Apache common\\commons-io-2.18.0\\LICENSE.txt"),"US-ASCII"));

      var b=System.currentTimeMillis();
        System.out.println(b-a);

    }
}