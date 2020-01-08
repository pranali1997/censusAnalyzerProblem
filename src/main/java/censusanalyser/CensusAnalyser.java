package censusanalyser;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.OpenCSVBuilder;

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


        public List SortingIndiaCSVFile(String csvFilePath) throws CensusAnalyserException{
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
            {
                Iterator<IndiaCensusCSV> censusCSVIterator = new OpenCSVBuilder().getCsvFileIterable(reader,IndiaCensusCSV.class);
                List<IndiaCensusCSV> listIndiaCSv = new ArrayList<>();
                while (censusCSVIterator.hasNext()) {
                    listIndiaCSv.add(censusCSVIterator.next());
                }
                Collections.sort(listIndiaCSv,new ObjectComparatorEx());
                System.out.println(listIndiaCSv);
                return listIndiaCSv;
            } catch (IOException e) {
              //  e.printStackTrace();
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


    public List SortingStateCodeCSVFile(String csvFilePath) throws CensusAnalyserException{
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            Iterator<CSVState> censusCSVIterator = new OpenCSVBuilder().getCsvFileIterable(reader,CSVState.class);
            List<CSVState> listStateCSv = new ArrayList<>();
            while (censusCSVIterator.hasNext()) {
                listStateCSv.add(censusCSVIterator.next());
            }
            Collections.sort(listStateCSv,new ObjectComparatorEx());
            System.out.println(listStateCSv);
            return listStateCSv;
        } catch (IOException e) {
            //  e.printStackTrace();
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



