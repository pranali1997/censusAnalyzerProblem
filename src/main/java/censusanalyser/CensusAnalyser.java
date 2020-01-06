package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            CsvToBean<IndiaCensusCSV> csvToBean =  new OpenCSVBuilder().getCsvFileIterable(reader,IndiaCensusCSV.class);
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            Iterable<IndiaCensusCSV> csvIterable=() -> censusCSVIterator;
            int namOfEateries= (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
            return namOfEateries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_DELIMITER);
        }
    }

    public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            CsvToBean<CSVState> csvToBean = OpenCSVBuilder.getCsvFileIterable(reader,CSVState.class);
            Iterator<CSVState> censusCSVIterator = csvToBean.iterator();
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
        }
    }

    private <E> int getCount(Iterator<E> iterator){
        Iterable<E> csvIterable=() -> iterator;
        int namOfEateries= (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
        return namOfEateries;
    }



}
