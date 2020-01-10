package censusanalyser;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class CensusAnalyser {

        List<IndiaCensusDAO> indiaCensusDAOList= null;
        List<StateCensusDAO> stateCensusDAOList=null;

    public CensusAnalyser() {
        this.indiaCensusDAOList=new ArrayList<IndiaCensusDAO>();
        this.stateCensusDAOList=new ArrayList<StateCensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> indiaCensusList = icsvBuilder.getCSVList(reader,IndiaCensusCSV.class);
            for (int i=0;i<indiaCensusList.size();i++)
            {
                this.indiaCensusDAOList.add(new IndiaCensusDAO(indiaCensusList.get(i)));
            }
            return indiaCensusDAOList.size();
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_DELIMITER);
        }catch (CSVBuilderException e){
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }
    }

    public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
            List<CSVState> csvStates = icsvBuilder.getCSVList(reader,CSVState.class);
            for (int i=0;i<csvStates.size();i++)
            {
                this.stateCensusDAOList.add(new StateCensusDAO(csvStates.get(i)));
            }return csvStates.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_DELIMITER);
        }catch (CSVBuilderException e){
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }
    }


    public String SortingIndiaCSVFile() throws CensusAnalyserException {
        if (indiaCensusDAOList ==null || indiaCensusDAOList.size()==0)
        {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        Comparator <IndiaCensusDAO> codeComparator = (o1, o2) -> ((o1.state).compareTo(o2.state) < 0) ? -1 : 1;
        Collections.sort(indiaCensusDAOList, codeComparator);
        String jsonString = new Gson().toJson(indiaCensusDAOList);
        return jsonString;

    }

    public String SortingStateCodeCSVFile() throws CensusAnalyserException{
        if ( stateCensusDAOList==null || stateCensusDAOList.size()==0)
        {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        Comparator<StateCensusDAO> codeComparator=(o1,o2) -> ((o1.stateCode).compareTo(o2.stateCode)<0)?-1:1;
            Collections.sort(stateCensusDAOList,codeComparator);
            String jsonString=new Gson().toJson(stateCensusDAOList);
            return jsonString;


    }

    public String SortIndiaStateCSVByPopulation() throws CensusAnalyserException {
        if (indiaCensusDAOList ==null || indiaCensusDAOList.size()==0)
        {
            throw new CensusAnalyserException("NO_CENSUS_DATA",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        Comparator <IndiaCensusDAO> codeComparator = (o1, o2) -> (Integer.compare(o1.population,o2.population) > 0) ? -1 : 1;
        Collections.sort(indiaCensusDAOList, codeComparator);
        String jsonString = new Gson().toJson(indiaCensusDAOList);
        System.out.println("according to population--->"+jsonString);
        return jsonString;

    }
}



