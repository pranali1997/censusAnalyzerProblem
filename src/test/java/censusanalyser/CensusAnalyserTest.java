package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class CensusAnalyserTest {


    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCode.csv";

    @Test
    public void givenIndianStateCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusCSVFileTypeIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusData.txt");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusCsvFileCorrect_ButDelimiterIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusDataWrong.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusCsvFileCorrectCSV_butIncorrectCSVHeader_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusDataWrong.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCensusData(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCensusData(WRONG_STATE_CODE_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFileTypeIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCode.txt");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCsvFileCorrect_ButDelimiterIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusDataWrong.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCsvFileCorrectCSV_butIncorrectCSVHeader_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCode.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }


    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenStateName_AndCheckedDataAtFirstPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.SortingIndiaCSVFile();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(true, indiaCensusCSV[0].state.contains("Andhra Pradesh"));
    }

    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenStateName_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.SortingIndiaCSVFile();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(true, indiaCensusCSV[28].state.contains("West Bengal"));
    }


    @Test
    public void givenStateCodeCensusCSVFile_ShouldGivenStateCode_AndCheckedDataAtFirstPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadStateCensusData(STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.SortingStateCodeCSVFile();
        CSVState[] indiaCensusCSV = new Gson().fromJson(censusCSVList, CSVState[].class);
        Assert.assertEquals(true, indiaCensusCSV[0].stateCode.contains("AD"));
    }

    @Test
    public void givenStateCodeCensusCSVFile_ShouldGivenStateCode_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadStateCensusData(STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.SortingStateCodeCSVFile();
        CSVState[] indiaCensusCSV = new Gson().fromJson(censusCSVList, CSVState[].class);
        Assert.assertEquals("WB", indiaCensusCSV[36].stateCode);
    }

    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenStatePopulation_AndCheckedDataAtFirstPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH );
        String censusCSVList = censusAnalyser.SortIndiaStateCSVByPopulation();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Uttar Pradesh", indiaCensusCSV[0].state);
    }


    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenStatePopulation_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH );
        String censusCSVList = censusAnalyser.SortIndiaStateCSVByPopulation();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Sikkim", indiaCensusCSV[28].state);
    }


    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenDensity_AndCheckedDataAtFirstPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.SortingIndiaCSVFileByDensity();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Bihar", indiaCensusCSV[0].state);
    }


    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenDensity_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.SortingIndiaCSVFileByDensity();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Arunachal Pradesh", indiaCensusCSV[28].state);
    }

    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenArea_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.SortingIndiaCSVFileByArea();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Rajasthan", indiaCensusCSV[0].state);
    }
    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenAreaDensity_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.SortingIndiaCSVFileByArea();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Goa", indiaCensusCSV[28].state);
    }

    @Test
    public void givenUSStateCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadUSCensusData("./src/test/resources/USCensusData .csv");
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }


}