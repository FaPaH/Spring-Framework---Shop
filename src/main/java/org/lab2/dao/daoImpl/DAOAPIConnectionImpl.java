package org.lab2.dao.daoImpl;

import org.apache.log4j.Logger;
import org.lab2.dao.DAOAPIConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@PropertySource("classpath:datasource-cfg.properties")
public class DAOAPIConnectionImpl implements DAOAPIConnection {

    private static Logger logger = Logger.getLogger(DAOAPIConnectionImpl.class);

    private static String labUrl;

    @Autowired
    public void setLabUrl(@Value("${lab2.url}") String labUrl) {
        this.labUrl = labUrl;
    }

    @Override
    public String getMessage(String movieName){
        try {
            URL url = new URL(labUrl + movieName);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
                response.append("\r");
            }
            reader.close();

            return response.toString();
        } catch (IOException e){
            logger.error("Error in DAOAPIConnectionImpl: " + e.getMessage());
        }
        return "Error";
    }
}
