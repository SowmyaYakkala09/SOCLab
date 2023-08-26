import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileWriter;

public class XMLParser {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("banking.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("banking");
            String[][] tableData = new String[nodeList.getLength()][4];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // Create table headers
                    Element bankingElement = (Element) nodeList.item(i);
                    String firstName = bankingElement.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = bankingElement.getElementsByTagName("lastName").item(0).getTextContent();
                    String pass = bankingElement.getElementsByTagName("pass").item(0).getTextContent();
                    String dob = bankingElement.getElementsByTagName("dob").item(0).getTextContent();
                    tableData[i][0] = firstName;
                    tableData[i][1] = lastName;
                    tableData[i][2] = pass;
                    tableData[i][3] = dob;
                }
                
            }
            generateHTMLTable(tableData,"table.html");
            System.out.println("HTML table generated successfully.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generateHTMLTable(String[][] tableData, String outputFilePath) 
    {
       try 
        {
            
            FileWriter writer = new FileWriter(outputFilePath);

            writer.write("<html><body>");
            writer.write("<table border=\"1\">");

            // Generate table header
            writer.write("<tr>");
            writer.write("<th>FirstName</th>");
            writer.write("<th>LastName</th>");
            writer.write("<th>Password</th>");
            writer.write("<th>Date of Birth</th>");
            writer.write("</tr>");

            
            // Generate table rows
            for (String[] row : tableData) 
            {
                writer.write("<tr>");
                for (String cell : row) 
                {
                    writer.write("<td>" + cell + "</td>");
                }
                writer.write("</tr>");
            }

            writer.write("</table>");
            writer.write("</body></html>");

            writer.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
