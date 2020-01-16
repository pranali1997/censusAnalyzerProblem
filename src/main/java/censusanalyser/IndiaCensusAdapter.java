package censusanalyser;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {

    public Map<String,CensusDAO> loadStateCensusData(String csvFilePath,Map<String,CensusDAO> censusStateMap) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVState> csvStates = icsvBuilder.getCSVList(reader, CSVState.class);
                StreamSupport.stream(csvStates.spliterator(),false)
                    .filter(CSVStateList->censusStateMap.get(CSVStateList.stateName)!=null)
                    .forEach(CSVStateList ->censusStateMap.get(CSVStateList.stateName).StateCode= CSVStateList.stateCode);

            return censusStateMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_DELIMITER);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    @Override
    public <E> Map<String ,CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException{
        Map<String, CensusDAO> censusStateMap=super.loadCensusData(IndiaCensusCSV.class,csvFilePath[0]);
        this.loadStateCensusData(csvFilePath[1],censusStateMap);
        return censusStateMap;
    }
}
