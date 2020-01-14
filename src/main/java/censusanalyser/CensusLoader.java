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
import java.util.TreeMap;
import java.util.stream.StreamSupport;

public class CensusLoader {



    public <E> Map<String,CensusDAO> combiningCensusData(Class<E> censusCSVClass, String... csvFilePath) throws CensusAnalyserException {
        Map<String,CensusDAO> censusStateMap = new TreeMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                List<E> censusList = icsvBuilder.getCSVList(reader, IndiaCensusCSV.class);
                StreamSupport.stream(censusList.spliterator(),false)
                       .map(IndiaCensusCSV.class::cast)
                       .forEach(censusCSV -> censusStateMap.put(censusCSV.state,new CensusDAO(censusCSV)));
            }
            if(censusCSVClass.getName().equals("censusanalyser.USCensusCSV")){
                List<E> censusList = icsvBuilder.getCSVList(reader, USCensusCSV.class);
                    StreamSupport.stream(censusList.spliterator(),false)
                            .map(USCensusCSV.class::cast)
                            .forEach(censusCSV -> censusStateMap.put(censusCSV.State,new CensusDAO(censusCSV)));
            }
            if (csvFilePath.length==1)return censusStateMap;
            this.loadStateCensusData(csvFilePath[1],censusStateMap);
            System.out.println(censusStateMap);
            return  censusStateMap;
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


    public Map<String,CensusDAO> loadStateCensusData(String csvFilePath,Map<String,CensusDAO> censusStateMap) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVState> csvStates = icsvBuilder.getCSVList(reader, CSVState.class);
                StreamSupport.stream(csvStates.spliterator(),false)
                    .filter(CSVStateList->censusStateMap.get(CSVStateList.stateName)!=null)
                    .forEach(CSVStateList ->censusStateMap.get(CSVStateList.stateName).State_Id= CSVStateList.stateCode);

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
}
