package org.PipesAndFilters;

import java.io.FileReader;
import java.util.Iterator;

import org.PipesAndFilters.exceptions.ConfigFileErrorException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReader {
    private final String CONFIG_PATH;

    public JSONReader(String configPath) {
        this.CONFIG_PATH = configPath;
    }

    public String getCONFIG_PATH() {
        return CONFIG_PATH;
    }

    public Object[] getJSONPhases() throws ConfigFileErrorException {
        Object obj;

        try {
            obj = new JSONParser().parse(new FileReader(CONFIG_PATH));
        } catch (Exception e) {
            throw new ConfigFileErrorException(e.getMessage());
        }
        JSONObject jsonConfig = (JSONObject) obj;

        JSONArray phasesJson = (JSONArray) jsonConfig.get("phases");

        return phasesJson.toArray();
    }

    public static void main(String[] args) {
        JSONReader filReader = new JSONReader("org/config/config.json");
        try {
            Object[] obj = filReader.getJSONPhases();
            for (Object object : obj) {
                System.out.println((String) object);
            }
        } catch (ConfigFileErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
