package censusanalyser;

import com.opencsv.bean.CsvToBean;

import java.io.Reader;

public interface ICSVBuilder {
    public  <E> CsvToBean<E> getCsvFileIterable(Reader reader, Class CSVClass) throws CensusAnalyserException;
}

