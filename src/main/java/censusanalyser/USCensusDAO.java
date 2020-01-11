package censusanalyser;

public class USCensusDAO {
    public   String State_Id;
    public String State;
    public   int Population;
    public int Housing_units;
    public double Total_Area;
    public double Water_Area;
    public double Land_Area;
    public double Population_Density;
    public double Housing_Density;

    public USCensusDAO(USCensusCSV usCensusCSV) {
        State_Id=usCensusCSV.State_Id;
        State=usCensusCSV.State;
        Population=usCensusCSV.Population;
        Housing_units=usCensusCSV.Housing_units;
        Total_Area=usCensusCSV.Total_Area;
        Water_Area=usCensusCSV.Water_Area;
        Land_Area=usCensusCSV.Land_Area;
        Population_Density=usCensusCSV.Population_Density;
        Housing_Density=usCensusCSV.Housing_Density;

    }
}
