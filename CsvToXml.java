import java.util.Scanner;
import java.io.*;
public class CsvToXml{
	
    public static void main(String[] args) throws IOException {
    	
        Scanner s = new Scanner(new File("src\\adres.csv"));
        FileReader fr=new FileReader("src\\adres.csv");    
        BufferedReader br=new BufferedReader(fr);
        
        FileWriter writer = new FileWriter("src\\deneme6.xml");  
        BufferedWriter buffer = new BufferedWriter(writer); 
        
        String xml ="";
        String[] arrOfStr = null;
        String[] arrOfStr2 = null;
        String words; 
        String words2;
        for (int i = 0; i < 1; i++) {

       	 words = br.readLine();
       	 arrOfStr = words.split(";");
           
           for (String a : arrOfStr)
               System.out.println(a);
       } 
        s.next();
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>");
        buffer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>");
        while(s.hasNext()){
           String[] fields = s.next().split(";");
           
           System.out.println("  <row>");
           buffer.write("  <row>");
           for(int i =0; i<arrOfStr.length; i++) {
           
           buffer.write("<"+arrOfStr[i]+">"+fields[i]+"</"+arrOfStr[i]+">");
           System.out.printf("    <%s>%s</%s>\n",arrOfStr[i] ,fields[i],arrOfStr[i]);
            
           //xml ="<"+arrOfStr[i]+">"+fields[i]+"</"+arrOfStr[i]+">"+xml;
        		             
           }
           System.out.println("  </row>");
           buffer.write("  </row>");
        }
        System.out.println("</root>");
        s.close();
        buffer.close();  
        System.out.println("Success");  

    }
}