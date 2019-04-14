package json.issue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Issue issue = new Issue();
        issue.setName("Igor!!!");

        String result="";
        try {
          result =  objectMapper.writeValueAsString(issue);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
