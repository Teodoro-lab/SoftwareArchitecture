package org.PipesAndFilters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.PipesAndFilters.exceptions.JSONParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {
    private final String CONFIG_PATH;

    public ConfigReader(String configPath) {
        this.CONFIG_PATH = configPath;
    }

    public String getCONFIG_PATH() {
        return CONFIG_PATH;
    }

    public JSONArray getJSONPhases() throws FileNotFoundException, IOException, JSONParseException {
        Object obj;

        try {
            obj = new JSONParser().parse(new FileReader(CONFIG_PATH));
        } catch (ParseException e) {
            throw new JSONParseException(e.getPosition(), e.getErrorType(), e.getUnexpectedObject(),
                    "Your JSON config file contains errors:");
        }

        JSONObject jsonConfig = (JSONObject) obj;

        JSONArray phasesJson = (JSONArray) jsonConfig.get("phases");

        return phasesJson;
    }
}
