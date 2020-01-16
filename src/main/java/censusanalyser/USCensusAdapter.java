package censusanalyser;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {

    @Override
    public <E> Map<String,CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException{
        Map<String,CensusDAO> censusStateMap=super.loadCensusData(USCensusCSV.class,csvFilePath);
        return censusStateMap;
    }

}
