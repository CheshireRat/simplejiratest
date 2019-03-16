package pages;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {


    public String readValue(String key){
        Properties prop = new Properties();
        InputStream input = null;
        try {

            input = new FileInputStream("src\\main\\java\\pages\\jira.properties");

            // load a properties file
            prop.load(input);



        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //is = new FileInputStream("src/main/resources/jira.properties");
        //property.load(fis);

        return  prop.getProperty(key);
    }
}