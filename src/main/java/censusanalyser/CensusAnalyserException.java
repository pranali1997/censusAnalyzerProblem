package censusanalyser;

public class CensusAnalyserException extends Exception {
    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type= ExceptionType.valueOf(name);
    }

   public enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,INVALID_DELIMITER
    }

    public ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
