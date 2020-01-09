package censusanalyser;

import CSVBuilder.CSVBuilderException;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
            Assert.assertEquals(29,numOfRecords);
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
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
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
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
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
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER,e.type);
        }
    }

    @Test
    public void givenIndianStateCensusCsvFileCorrectCSV_butIncorrectCSVHeader_ShouldREturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCensusDataWrong.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER,e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCensusData(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
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
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
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
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
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
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER,e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCsvFileCorrectCSV_butIncorrectCSVHeader_ShouldREturnCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCensusData("/home/admin1/Downloads/CensusAnalyser/src/test/resources/IndiaStateCode.csv");
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER,e.type);
        }
    }


    @Test
    public void givenWrongIndianStateCensusCSVFile_ShouldCheckLastState()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List list= Collections.singletonList(censusAnalyser.SortingIndiaCSVFile(WRONG_CSV_FILE_PATH));
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }

    }

    @Test
    public void givenWrongStateCodeCensusCSVFile_ShouldCheckLastState()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String list= censusAnalyser.SortingStateCodeCSVFile(WRONG_STATE_CODE_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }

    }
    @Test
    public void givenIndianStateCensusCSVFile_ShouldCheckFirstState() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        String censusCSVList = censusAnalyser.SortingIndiaCSVFile(INDIA_CENSUS_CSV_FILE_PATH);
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSV[0].state.contains("Andhra Pradesh"));
    }

    @Test
    public void givenIndianStateCensusCSVFile_ShouldCheckLastState() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        String censusCSVList = censusAnalyser.SortingIndiaCSVFile(INDIA_CENSUS_CSV_FILE_PATH);
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSV[28].state.contains("West Bengal"));
    }


    @Test
    public void givenStateCodeCensusCSVFile_ShouldCheckFirstState() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        String censusCSVList = censusAnalyser.SortingIndiaCSVFile(INDIA_CENSUS_CSV_FILE_PATH);
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSV[0].state.contains("Andhra Pradesh"));
    }

    @Test
    public void givenStateCodeCensusCSVFile_ShouldCheckLastState() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        String censusCSVList = censusAnalyser.SortingIndiaCSVFile(INDIA_CENSUS_CSV_FILE_PATH);
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSV[28].state.contains("West Bengal"));
    }
}
