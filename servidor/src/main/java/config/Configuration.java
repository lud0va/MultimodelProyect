/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;


import jakarta.inject.Singleton;
import lombok.Getter;


import java.io.IOException;
import java.util.Properties;


@Singleton
@Getter
public class Configuration {

    private static final Configuration instance = null;
    private final Properties p;

    private Configuration() {
        try {
            p = new Properties();
            p.loadFromXML(Configuration.class.getClassLoader().getResourceAsStream(Constants.CONFIG_FILES_PROPERTIES_XML));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getProperty(String key) {
        return p.getProperty(key);
    }

}
