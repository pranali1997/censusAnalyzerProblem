package censusanalyser;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CensusLoader {
    List<CensusDAO> CensusDAOList = new ArrayList<>();

    public <E> List<CensusDAO> combiningCensusData(String csvFilePath, Class<E> censusCSVClass) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                List<E> censusList = icsvBuilder.getCSVList(reader, IndiaCensusCSV.class);
                for (int i = 0; i < censusList.size(); i++) {
                    this.CensusDAOList.add(new CensusDAO((IndiaCensusCSV) censusList.get(i)));
                }
            }
            if(censusCSVClass.getName().equals("censusanalyser.USCensusCSV")){
                List<E> censusList = icsvBuilder.getCSVList(reader, USCensusCSV.class);
                for (int i = 0; i < censusList.size(); i++) {
                    this.CensusDAOList.add(new CensusDAO((USCensusCSV) censusList.get(i)));
                }

            }
            return CensusDAOList;
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
        }    }
}
