package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVState {

        @CsvBindByName(column = "srNo", required = true)
        public int srNo;

        @CsvBindByName(column = "StateName", required = true)
        public String stateName;

        @CsvBindByName(column = "TIN", required = true)
        public int TIN;

        @CsvBindByName(column = "stateCode", required = true)
        public String stateCode;


        @Override
        public String toString() {
            return "IndiaCensusCSV{" +
                    "StateName='" + stateName + '\'' +
                    ", TIN='" + TIN + '\'' +
                    ", stateCode='" + stateCode + '\'' +
                    ", sr. No='" + srNo + '\'' +
                    '}';
        }
}
