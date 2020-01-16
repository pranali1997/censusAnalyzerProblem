package censusanalyser;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    private Country country;

    public CensusAnalyser() {

    }

    public enum Country{INDIA,US}

    Map<String,CensusDAO> CensusDAOMap =  new TreeMap<>();

    public CensusAnalyser(Country country) {
        this.country =country;
    }

    public int loadCensusData(String... csvFilePath) throws CensusAnalyserException{
        CensusDAOMap= CensusAdapterFactory.getCensusData(country,csvFilePath);
        return CensusDAOMap.size();
    }


    public String SortingIndiaCSVFile() throws CensusAnalyserException, IOException {
        if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }

        List codeCensusList=CensusDAOMap.values().stream()
                .sorted(Comparator.comparing(data -> data.state))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList());
        String jsonString = new Gson().toJson(codeCensusList);
        System.out.println(jsonString);
        return jsonString;

    }

   public String SortingStateCodeCSVFile() throws CensusAnalyserException {
         if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }

       List codeCensusList=CensusDAOMap.values().stream()
               .sorted((Comparator.comparing(censusData -> censusData.StateCode)))
                 .map(censusDAO -> censusDAO.getCensusDTO(country))
                 .collect(Collectors.toList());


       //Collections.sort( codeCensusList,codeComparator);
        String jsonString = new Gson().toJson(codeCensusList);
        return jsonString;
   }

    public String SortIndiaStateCSVByPopulation() throws CensusAnalyserException {
        if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }


        List codeCensusList=CensusDAOMap.values().stream()
                .sorted((Comparator.comparing(censusData -> censusData.population)))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList());
                Collections.reverse(codeCensusList);
        String jsonString = new Gson().toJson(codeCensusList);
        System.out.println(jsonString);
        return jsonString;

    }

    public String SortingIndiaCSVFileByDensity() throws CensusAnalyserException {
        if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }


        List codeCensusList=CensusDAOMap.values().stream()
                .sorted((Comparator.comparing(censusData -> censusData.densityPerSqKm)))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList());
                Collections.reverse(codeCensusList);
        String jsonString = new Gson().toJson(codeCensusList);
        return jsonString;

    }

    public String SortingIndiaCSVFileByArea() throws CensusAnalyserException {
        if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }


        List codeCensusList=CensusDAOMap.values().stream()
                .sorted((Comparator.comparing(censusData -> censusData.areaInSqKm)))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList());
        String jsonString = new Gson().toJson(codeCensusList);
        System.out.println(jsonString);
        return jsonString;

    }



}



