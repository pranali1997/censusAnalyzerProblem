package censusanalyser;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {

    public Map<String,CensusDAO> loadUsCensusData(String... csvFilePath) throws CensusAnalyserException{

        Map<String,CensusDAO> censusStateMap=super.combiningCensusData(USCensusCSV.class,csvFilePath);
        //this.loadUsCensusData(csvFilePath[1],censusStateMap);
        return censusStateMap;
    }

}
