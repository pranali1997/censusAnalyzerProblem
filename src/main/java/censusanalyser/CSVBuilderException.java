package censusanalyser;

public class CSVBuilderException extends Exception {


    public CSVBuilderException(String message, String name) {
        super(message);
        this.type=ExceptionType.valueOf(name);
    }

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,INVALID_DELIMITER
    }
    ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type=type;
    }
}
