package censusanalyser;


import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
    public Iterator<E> getCsvFileIterable(Reader reader, Class<E> CSVClass) throws CensusAnalyserException, CSVBuilderException;
}

