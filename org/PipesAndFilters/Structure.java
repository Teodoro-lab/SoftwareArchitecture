package org.PipesAndFilters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.PipesAndFilters.exceptions.ConfigFileErrorException;
import org.PipesAndFilters.exceptions.FilterCreationAbortedException;
import org.PipesAndFilters.exceptions.FilterExecutionException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Structure {
    private final String CONFIG_PATH;

    private Pipe inPipe;
    private Pipe outPipe;

    public Structure(String configPath) {
        this.CONFIG_PATH = configPath;
    }

    public String getCONFIG_PATH() {
        return CONFIG_PATH;
    }

    public void sendInput(Object obj) throws FilterExecutionException {
        try {
            inPipe.sendInfoToFilter(obj);
        } catch (Exception e) {
            throw new FilterExecutionException(
                    "Filter in incorrect order or method not implemented: " + e.getMessage());
        }
    }

    public Object getOutput() {
        return outPipe.getInformation();
    }

    private Class<?> filterTypeClass(String className) throws ClassNotFoundException {
        Class<?> filterExtendedClass;
        filterExtendedClass = Class.forName(className);
        return filterExtendedClass;
    }

    private Constructor<?> getFilterTypeConstructor(Class<?> objClass)
            throws NoSuchMethodException, SecurityException {
        Constructor<?> constructor;
        constructor = objClass.getConstructor();
        return constructor;
    }

    private Filter createFilterInstance(Constructor<?> constructor)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object object;
        object = constructor.newInstance();
        return (Filter) object;
    }

    private Filter getFilterInstance(String className) throws FilterCreationAbortedException {
        Filter filter;

        try {
            Class<?> objClass = filterTypeClass(className);
            Constructor<?> objConstructor = getFilterTypeConstructor(objClass);
            filter = createFilterInstance(objConstructor);
        } catch (Exception e) {
            throw new FilterCreationAbortedException("Your config JSON contains an error:" + e.getMessage());
        }

        return filter;
    }

    private void joinUpPipesFilters(Filter[] filters, Pipe[] pipes) {
        for (int i = 0; i < filters.length; i++) {
            pipes[i].setNextFilter(filters[i]);
            filters[i].setNextPipe(pipes[i + 1]);
        }
    }

    private JSONArray getPhases() throws ConfigFileErrorException {
        Object obj;

        try {
            obj = new JSONParser().parse(new FileReader(CONFIG_PATH));
        } catch (Exception e) {
            throw new ConfigFileErrorException(e.getMessage());
        }
        JSONObject jsonConfig = (JSONObject) obj;

        JSONArray phasesJson = (JSONArray) jsonConfig.get("phases");

        return phasesJson;
    }

    private void populateFiltersAndPipes(JSONArray phasesJson, Filter[] filters, Pipe[] pipes)
            throws FilterCreationAbortedException {

        Iterator<?> itr2 = phasesJson.iterator();
        Iterator<Map.Entry> itr1;

        while (itr2.hasNext()) {
            itr1 = ((Map) itr2.next()).entrySet().iterator();

            String className = (String) itr1.next().getValue();
            int order = Integer.parseInt((String) itr1.next().getValue());

            filters[order - 1] = getFilterInstance(className);
            pipes[order - 1] = new Pipe();
        }

        pipes[filters.length] = new Pipe();
    }

    public void parseConfig()
            throws FileNotFoundException, IOException, ParseException, FilterCreationAbortedException,
            ConfigFileErrorException {

        JSONArray phasesJson = getPhases();

        Filter[] filters = new Filter[phasesJson.size()];
        Pipe[] pipes = new Pipe[phasesJson.size() + 1];

        populateFiltersAndPipes(phasesJson, filters, pipes);
        joinUpPipesFilters(filters, pipes);

        this.inPipe = pipes[0];
        this.outPipe = pipes[phasesJson.size()];
    }
}
