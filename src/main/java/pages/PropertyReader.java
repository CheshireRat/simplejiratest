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
//TODO - find out how to avoid path and where should properties stored
            input = new FileInputStream("src\\main\\java\\pages\\jira.properties");

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


        return  prop.getProperty(key);
    }
}