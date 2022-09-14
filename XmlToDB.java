import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class XmlToDB {
	
    private String kullanici_adi = "root";
    private String parola = "";
    private String db_ismi = "demo";
    private String host =  "localhost"; 
    private int port = 3306;
    private static Connection con = null;
    private static Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private static String name;
    
    public XmlToDB() {
        
        // "jbdc:mysql://localhost:3306/demo" 
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi+ "?useUnicode=true&characterEncoding=utf8";
        
        try {       
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
               
        try {
            con = DriverManager.getConnection(url, kullanici_adi, parola);
            System.out.println("Bağlantı Başarılı...");
              
        } catch (SQLException ex) {
            //System.out.println("Bağlantı Başarısız...");
            ex.printStackTrace();
        }
    	}
    
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

    	XmlToDB baglanti = new XmlToDB();
    	
    	try {
    		statement = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	    	
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		
    	Document doc = docBuilder.parse (new File("src\\deneme6.xml"));
    	
    	doc.getDocumentElement().normalize();
    	NodeList listOfPersons = doc.getElementsByTagName("row");
    	
    	for(int s=0; s<listOfPersons.getLength(); s++){
    		Node firstPersonNode = listOfPersons.item(s);
    		if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
    			
    			//For FirstName
    			Element firstPersonElement = (Element)firstPersonNode;
    			NodeList nameList = firstPersonElement.getElementsByTagName("FirstName");
    			Element nameElement =(Element)nameList.item(0);

    			NodeList textFNList = nameElement.getChildNodes();
    			name=((Node)textFNList.item(0)).getNodeValue().trim();

    			//For LastName
    			NodeList LastList = firstPersonElement.getElementsByTagName("LastName");
    			Element last =(Element)LastList.item(0);

    			NodeList textLNList = last.getChildNodes();
    			String lastname= ((Node)textLNList.item(0)).getNodeValue().trim();
    			
    			//For Age
    			NodeList AgeList = firstPersonElement.getElementsByTagName("Age");
    			Element age =(Element)AgeList.item(0);

    			NodeList textage = age.getChildNodes();
    			String ageLast= ((Node)textage.item(0)).getNodeValue().trim();
    			
    			int intAge = Integer.parseInt(ageLast);
    			
    			//For City
    			NodeList cityList = firstPersonElement.getElementsByTagName("City");
    			Element city =(Element)cityList.item(0);

    			NodeList textcity = city.getChildNodes();
    			String cityLast= ((Node)textcity.item(0)).getNodeValue().trim();

    			System.out.println(name+" "+lastname+" "+ageLast+" "+cityLast);
    			
    			 try {
    				 statement = con.createStatement();
    				 statement.executeUpdate("Insert Into worker(FirstName,LastName,City,Age) VALUES ("+"'"+name+"',"+"'"+lastname+"',"+"'"+cityLast+"',"+"'"+intAge+"')");
					System.out.println("Yüklendi");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Hata");
				} 
    			}
    			}	
    	}
    	}
