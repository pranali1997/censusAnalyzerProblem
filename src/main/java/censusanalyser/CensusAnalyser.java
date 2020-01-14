package censusanalyser;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {



    Map<String,CensusDAO> CensusDAOMap = null;
    public CensusAnalyser() {
        this.CensusDAOMap = new TreeMap<>();
    }



    public int loadIndiaCensusData(String... csvFilePath) throws CensusAnalyserException {
        CensusDAOMap =new CensusLoader().combiningCensusData(IndiaCensusCSV.class,csvFilePath);
        System.out.println(CensusDAOMap);
              return CensusDAOMap.size();
    }


    public int loadUSCensusData(String... csvFilePath) throws CensusAnalyserException {
        CensusDAOMap = new CensusLoader().combiningCensusData(USCensusCSV.class,csvFilePath);
        System.out.println(CensusDAOMap);
        return CensusDAOMap.size();
    }


    public String SortingIndiaCSVFile() throws CensusAnalyserException, IOException {
        if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }

        List <CensusDAO> sortedList = CensusDAOMap.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeComparator = (o1, o2) -> ((o1.state).compareTo(o2.state) < 0) ? -1 : 1;
        Collections.sort(sortedList, codeComparator);
        String jsonString = new Gson().toJson(sortedList);
        System.out.println(jsonString);
        return jsonString;

    }

   public String SortingStateCodeCSVFile() throws CensusAnalyserException {
         if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
       List <CensusDAO> sortedList = CensusDAOMap.values().stream().collect(Collectors.toList());
       Comparator<CensusDAO> codeComparator = (o1, o2) -> ((o1.population - (o2.population)) > 0) ? -1 : 1;
       Collections.sort(sortedList, codeComparator);
        String jsonString = new Gson().toJson(sortedList);
        return jsonString;
   }

    public String SortIndiaStateCSVByPopulation() throws CensusAnalyserException {
        if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sortedList = CensusDAOMap.values().stream().collect(Collectors.toList());

        Comparator<CensusDAO> codeComparator = (o1, o2) -> (Integer.compare(o1.population, o2.population) > 0) ? -1 : 1;
        Collections.sort(sortedList, codeComparator);
        String jsonString = new Gson().toJson(sortedList);
        return jsonString;

    }

    public String SortingIndiaCSVFileByDensity() throws CensusAnalyserException {
        if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sortedList = CensusDAOMap.values().stream().collect(Collectors.toList());

        Comparator<CensusDAO> codeComparator = (o1, o2) -> (Integer.compare(o1.densityPerSqKm, o2.densityPerSqKm) > 0) ? -1 : 1;
        Collections.sort(sortedList, codeComparator);
        String jsonString = new Gson().toJson(sortedList);
        return jsonString;

    }

    public String SortingIndiaCSVFileByArea() throws CensusAnalyserException {
        if (CensusDAOMap == null || CensusDAOMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sortedList = CensusDAOMap.values().stream().collect(Collectors.toList());

        Comparator<CensusDAO> codeComparator = (o1, o2) -> (Integer.compare(o1.areaInSqKm, o2.areaInSqKm) > 0) ? -1 : 1;
        Collections.sort(sortedList,codeComparator);
        String jsonString = new Gson().toJson(sortedList);
        return jsonString;

    }



}



