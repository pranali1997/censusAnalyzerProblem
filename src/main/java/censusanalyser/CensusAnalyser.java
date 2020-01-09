package censusanalyser;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.OpenCSVBuilder;
import org.json.JSONArray;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            Iterator<IndiaCensusCSV> censusCSVIterator = new OpenCSVBuilder().getCsvFileIterable(reader,IndiaCensusCSV.class);
            SortingIndiaCSVFile(csvFilePath);
            return getCount(censusCSVIterator);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            Iterator<CSVState> censusCSVIterator = new OpenCSVBuilder().getCsvFileIterable(reader,CSVState.class);
            SortingStateCodeCSVFile(csvFilePath);
            return getCount(censusCSVIterator);
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

    private <E> int getCount(Iterator<E> iterator){
        Iterable<E> csvIterable=() -> iterator;
        int namOfEateries= (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
        return namOfEateries;
    }


        public JSONArray SortingIndiaCSVFile(String csvFilePath) throws CensusAnalyserException{
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
            {
                Iterator<IndiaCensusCSV> censusCSVIterator = new OpenCSVBuilder().getCsvFileIterable(reader,IndiaCensusCSV.class);
                List listIndiaCSv = new ArrayList();
                while (censusCSVIterator.hasNext()) {
                    listIndiaCSv.add(censusCSVIterator.next());
                }
                Comparator<IndiaCensusCSV> codeComparator=(o1,o2) -> ((o1.toString()).compareTo(o2.toString())<0)?-1:1;
                Collections.sort(listIndiaCSv,codeComparator);
                JSONArray jsonArray=new JSONArray();
                for(int i=0;i<listIndiaCSv.size();i++)
                {
                    jsonArray.put(listIndiaCSv.get(i));
                }
               // String prettyJson=new GsonBuilder().setPrettyPrinting().create().toJson(listIndiaCSv);
                System.out.println(jsonArray);
                return jsonArray;
            } catch (IOException e) {
            } catch (CSVBuilderException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            }catch (RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.INVALID_DELIMITER);
            }
            return null;
        }


    public JSONArray SortingStateCodeCSVFile(String csvFilePath) throws CensusAnalyserException{
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            Iterator<CSVState> censusCSVIterator = new OpenCSVBuilder().getCsvFileIterable(reader,CSVState.class);
            List<CSVState> listStateCSv = new ArrayList<>();
            while (censusCSVIterator.hasNext()) {
                listStateCSv.add(censusCSVIterator.next());
            }
            Comparator<CSVState> codeComparator=(o1,o2) -> ((o1.stateCode).compareTo(o2.stateCode)<0)?-1:1;
            Collections.sort(listStateCSv,codeComparator);
            JSONArray jsonArray=new JSONArray();
           // String prettyJson=new GsonBuilder().setPrettyPrinting().create().toJson(listStateCSv);
            for(int i=0;i<listStateCSv.size();i++)
            {
                jsonArray.put(listStateCSv.get(i));
            }
            System.out.println(jsonArray);
            return jsonArray;
        } catch (IOException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_DELIMITER);
        }
        return null;
    }

}



