package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;


public class CensusAnalyserTest {


    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_FILE_PATH = "/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCode.csv";

    @Test
    public void givenIndianStateCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            int numOfRecords = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusCSVFileTypeIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusData.txt");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusCsvFileCorrect_ButDelimiterIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusDataWrong.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusCsvFileCorrectCSV_butIncorrectCSVHeader_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusDataWrong.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            int numOfRecords = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,WRONG_STATE_CODE_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFileTypeIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,"/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCode.txt");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCsvFileCorrect_ButDelimiterIncorrect_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,"/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusDataWrong.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCsvFileCorrectCSV_butIncorrectCSVHeader_ShouldReturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,"/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCode.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        }
    }


    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenStateName_AndCheckedDataAtFirstPosition() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaCSVFile();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(true, indiaCensusCSV[0].state.contains("Andhra Pradesh"));
    }

    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenStateName_AndCheckedDataAtLastPosition() throws CensusAnalyserException, IOException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaCSVFile();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(true, indiaCensusCSV[28].state.contains("West Bengal"));
    }


    @Test
    public void givenStateCodeCensusCSVFile_ShouldGivenStateCode_AndCheckedDataAtFirstPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingStateCodeCSVFile();
        CensusDAO[] indiaCensusCSV = new Gson().fromJson(censusCSVList, CensusDAO[].class);
        Assert.assertEquals("Andhra Pradesh", indiaCensusCSV[0].state);
    }

    @Test
    public void givenStateCodeCensusCSVFile_ShouldGivenStateCode_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingStateCodeCSVFile();
        CensusDAO[] indiaCensusCSV = new Gson().fromJson(censusCSVList, CensusDAO[].class);
        Assert.assertEquals("West Bengal", indiaCensusCSV[28].state);
    }

    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenStatePopulation_AndCheckedDataAtFirstPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaStateCSVByPopulation();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Uttar Pradesh", indiaCensusCSV[0].state);
    }


    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenStatePopulation_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH );
        String censusCSVList = censusAnalyser.sortIndiaStateCSVByPopulation();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Sikkim", indiaCensusCSV[28].state);
    }


    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenDensity_AndCheckedDataAtFirstPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaCSVFileByDensity();
        CensusDAO[] indiaCensusCSV = new Gson().fromJson(censusCSVList, CensusDAO[].class);
        Assert.assertEquals("Bihar", indiaCensusCSV[0].state);
    }


    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenDensity_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaCSVFileByDensity();
        CensusDAO[] indiaCensusCSV = new Gson().fromJson(censusCSVList, CensusDAO[].class);
        Assert.assertEquals("Arunachal Pradesh", indiaCensusCSV[28].state);
    }

    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenArea_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaCSVFileByArea();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Goa", indiaCensusCSV[0].state);
    }
    @Test
    public void givenIndianStateCensusCSVFile_ShouldGivenAreaDensity_AndCheckedDataAtLastPosition() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaCSVFileByArea();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals("Rajasthan", indiaCensusCSV[28].state);
    }

    @Test
    public void givenUSStateCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            int numOfRecords = censusAnalyser.loadCensusData("./src/test/resources/USCensusData .csv");
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }


}