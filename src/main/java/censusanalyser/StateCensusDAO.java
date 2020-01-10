package censusanalyser;

public class StateCensusDAO {
    private int srNo;
    private String stateName;
    private int TIN;
    public String stateCode;

    public StateCensusDAO(CSVState csvState) {
        srNo=csvState.srNo;
        stateName=csvState.stateName;
        TIN=csvState.TIN;
        stateCode=csvState.stateCode;
    }

}
