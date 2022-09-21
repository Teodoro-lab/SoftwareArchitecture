package org.PipesAndFilters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.PipesAndFilters.exceptions.FilterCreationAbortedException;
import org.PipesAndFilters.exceptions.FilterExecutionException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

public class FilterPipeline {

    private Pipe inPipe;
    private Pipe outPipe;

    public void sendInput(Object obj) throws FilterExecutionException {
        try {
            inPipe.sendInfoToFilter(obj);
        } catch (NullPointerException e) {
            throw new FilterExecutionException(
                    "parseConfig method as not been called before trying to access IO in structure: " + e.getMessage());
        } catch (Exception e) {
            throw new FilterExecutionException(
                    "Filter in incorrect order or method not implemented: " + e.getMessage());
        }
    }

    public Object getOutput() throws FilterExecutionException {
        Object outputObject;

        try {
            outputObject = outPipe.getInformation();
        } catch (NullPointerException e) {
            throw new FilterExecutionException(
                    "parseConfig method as not been called before trying to access IO in structure: " + e.getMessage());
        }

        return outputObject;
    }

    private void joinUpPipesFilters(Filter[] filters, Pipe[] pipes) {
        for (int i = 0; i < filters.length; i++) {
            pipes[i].setNextFilter(filters[i]);
            filters[i].setNextPipe(pipes[i + 1]);
        }
    }

    private void populateFiltersAndPipes(Iterator<?> phasesJson, Filter[] filters, Pipe[] pipes)
            throws FilterCreationAbortedException, ClassNotFoundException, NoSuchMethodException {

        Map<String, String> phaseInformation;

        while (phasesJson.hasNext()) {
            phaseInformation = ((Map) phasesJson.next());

            String className = phaseInformation.get("class");
            int order = Integer.parseInt(phaseInformation.get("order"));

            FilterManager filterManager = new FilterManager();
            filters[order - 1] = filterManager.getFilterInstance(className);
            pipes[order - 1] = new Pipe();
        }

        pipes[filters.length] = new Pipe();
    }

    public void parseConfig(String path)
            throws FileNotFoundException, IOException, ParseException, FilterCreationAbortedException,
            ClassNotFoundException, NoSuchMethodException {

        JSONArray phasesJson = new ConfigReader(path).getJSONPhases();
        Iterator<?> phasesIter = phasesJson.iterator();

        Filter[] filters = new Filter[phasesJson.size()];
        Pipe[] pipes = new Pipe[phasesJson.size() + 1];

        populateFiltersAndPipes(phasesIter, filters, pipes);
        joinUpPipesFilters(filters, pipes);

        this.inPipe = pipes[0];
        this.outPipe = pipes[phasesJson.size()];
    }
}
