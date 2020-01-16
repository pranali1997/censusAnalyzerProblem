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

    Map<String,CensusDAO> censusDAOMap =  new TreeMap<>();

    public CensusAnalyser(Country country) {
        this.country =country;
    }

    public int loadCensusData(String... csvFilePath) throws CensusAnalyserException{
        censusDAOMap = CensusAdapterFactory.getCensusData(country,csvFilePath);
        return censusDAOMap.size();
    }


    public String sortingIndiaCSVFile() throws CensusAnalyserException, IOException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }

        List codeCensusList= censusDAOMap.values().stream()
                .sorted(Comparator.comparing(data -> data.state))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList());
        String jsonString = new Gson().toJson(codeCensusList);
        return jsonString;

    }

   public String sortingStateCodeCSVFile() throws CensusAnalyserException {
         if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }

       List codeCensusList= censusDAOMap.values().stream()
               .sorted((Comparator.comparing(censusData -> censusData.stateCode)))
                 .map(censusDAO -> censusDAO.getCensusDTO(country))
                 .collect(Collectors.toList());

        String jsonString = new Gson().toJson(codeCensusList);
        return jsonString;
   }

    public String sortIndiaStateCSVByPopulation() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }


        List codeCensusList= censusDAOMap.values().stream()
                .sorted((Comparator.comparing(censusData -> censusData.population)))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList());
                Collections.reverse(codeCensusList);
        String jsonString = new Gson().toJson(codeCensusList);
        return jsonString;

    }

    public String sortingIndiaCSVFileByDensity() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }


        List codeCensusList= censusDAOMap.values().stream()
                .sorted((Comparator.comparing(censusData -> censusData.densityPerSqKm)))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList());
                Collections.reverse(codeCensusList);
        String jsonString = new Gson().toJson(codeCensusList);
        return jsonString;

    }

    public String sortingIndiaCSVFileByArea() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }


        List codeCensusList= censusDAOMap.values().stream()
                .sorted((Comparator.comparing(censusData -> censusData.areaInSqKm)))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toList());
        String jsonString = new Gson().toJson(codeCensusList);
        return jsonString;

    }



}



