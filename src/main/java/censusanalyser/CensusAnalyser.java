package censusanalyser;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    List<CensusDAO> CensusDAOList = null;
    List<StateCensusDAO> stateCensusDAOList = null;

    public CensusAnalyser() {
        this.stateCensusDAOList = new ArrayList<StateCensusDAO>();
        this.CensusDAOList = new ArrayList<CensusDAO>();
    }



    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
              return this.combiningCensusData(csvFilePath,IndiaCensusCSV.class);
    }


    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
            return this.combiningCensusData(csvFilePath,USCensusCSV.class);
    }



    private <E> int combiningCensusData(String csvFilePath, Class<E> IndiacensusClass) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            if (IndiacensusClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                List<E> censusList = icsvBuilder.getCSVList(reader, IndiaCensusCSV.class);
                for (int i = 0; i < censusList.size(); i++) {
                    this.CensusDAOList.add(new CensusDAO((IndiaCensusCSV) censusList.get(i)));
                }
            }
            if(IndiacensusClass.getName().equals("censusanalyser.USCensusCSV")){
                List<E> censusList = icsvBuilder.getCSVList(reader, USCensusCSV.class);
                for (int i = 0; i < censusList.size(); i++) {
                    this.CensusDAOList.add(new CensusDAO((USCensusCSV) censusList.get(i)));
                }

            }
            return CensusDAOList.size();
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



    public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVState> csvStates = icsvBuilder.getCSVList(reader, CSVState.class);
            csvStates.stream().filter(stateCensusData -> stateCensusDAOList.add(new StateCensusDAO(stateCensusData))).collect(Collectors.toList());
            return csvStates.size();
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


    public String SortingIndiaCSVFile() throws CensusAnalyserException {
        if (CensusDAOList == null || CensusDAOList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        Comparator<CensusDAO> codeComparator = (o1, o2) -> ((o1.state).compareTo(o2.state) < 0) ? -1 : 1;
        Collections.sort(CensusDAOList, codeComparator);
        String jsonString = new Gson().toJson(CensusDAOList);
        return jsonString;

    }

    public String SortingStateCodeCSVFile() throws CensusAnalyserException {
        if (stateCensusDAOList == null || stateCensusDAOList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List<StateCensusDAO> censusList = stateCensusDAOList.stream().sorted(Comparator.comparing(StateCensusDAO::getStateCode)).collect(Collectors.toList());
        String jsonString = new Gson().toJson(censusList);
        return jsonString;
    }

    public String SortIndiaStateCSVByPopulation() throws CensusAnalyserException {
        if (CensusDAOList == null || CensusDAOList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        Comparator<CensusDAO> codeComparator = (o1, o2) -> (Integer.compare(o1.population, o2.population) > 0) ? -1 : 1;
        Collections.sort(CensusDAOList, codeComparator);
        String jsonString = new Gson().toJson(CensusDAOList);
        return jsonString;

    }

    public String SortingIndiaCSVFileByDensity() throws CensusAnalyserException {
        if (CensusDAOList == null || CensusDAOList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        Comparator<CensusDAO> codeComparator = (o1, o2) -> (Integer.compare(o1.densityPerSqKm, o2.densityPerSqKm) > 0) ? -1 : 1;
        Collections.sort(CensusDAOList, codeComparator);
        String jsonString = new Gson().toJson(CensusDAOList);
        return jsonString;

    }

    public String SortingIndiaCSVFileByArea() throws CensusAnalyserException {
        if (CensusDAOList == null || CensusDAOList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        Comparator<CensusDAO> codeComparator = (o1, o2) -> (Integer.compare(o1.areaInSqKm, o2.areaInSqKm) > 0) ? -1 : 1;
        Collections.sort(CensusDAOList, codeComparator);
        String jsonString = new Gson().toJson(CensusDAOList);
        return jsonString;

    }



}



