import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ExmoAPI {
    private static final Logger log = Logger.getLogger(ExmoAPI.class);
    private URL url;
    private HttpURLConnection con;
    private ExmoModel model;

    public ExmoModel ExmoConnectAPI(Kurs kurs) {
        try {
            url = new URL("https://api.exmo.com/v1.1/order_book?pair=" + kurs);
        } catch (MalformedURLException e) {
            log.error("MalformedURLException");
            e.printStackTrace();
        }
        ExmoModel exmoModel = null;

        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            log.error("IOException");
            e.printStackTrace();
        }

        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            log.error("ProtocolException");
            e.printStackTrace();
        }
        con.setRequestProperty("Content-Type", "application/json");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            System.out.println(content);
            exmoModel =  ModelReturner(kurs, content.toString());

        } catch (IOException e) {
            log.error("IOException");
            e.printStackTrace();
        }

        return exmoModel;
    }

    private ExmoModel ModelReturner(Kurs kurs, String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ExmoModel model = null;

        if (kurs == Kurs.BTC_USD) {
            //Deserialization
            ExmoUSD exmoUSD = mapper.readValue(content, ExmoUSD.class);
            //Serialization
            String jsonString = mapper.writeValueAsString(exmoUSD);
            System.out.println(jsonString);
            model = exmoUSD.getBTC_USD();
        } else if (kurs == Kurs.BTC_EUR) {
            //Deserialization
            ExmoEUR exmoEUR = mapper.readValue(content, ExmoEUR.class);
            //Serialization
            String jsonString = mapper.writeValueAsString(exmoEUR);
            System.out.println(jsonString);
            model = exmoEUR.getBTC_EUR();
        } else if (kurs == Kurs.BTC_UAH) {
            //Deserialization
            ExmoUAH exmoUAH = mapper.readValue(content, ExmoUAH.class);
            //Serialization
            String jsonString = mapper.writeValueAsString(exmoUAH);
            System.out.println(jsonString);
            model = exmoUAH.getBTC_UAH();
        }
        return model;
    }
}
