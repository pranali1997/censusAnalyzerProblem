package censusanalyser;

public class CensusDAO {
    public String state;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;


    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public String StateCode;
    public int Housing_units;
    public double Total_Area;
    public double Water_Area;
    public double Land_Area;
    public double Population_Density;
    public double Housing_Density;

    public CensusDAO(USCensusCSV usCensusCSV) {
        StateCode = usCensusCSV.State_Id;
        state = usCensusCSV.State;
        population = usCensusCSV.population;
        Housing_units = usCensusCSV.Housing_units;
        Total_Area = usCensusCSV.Total_Area;
        Water_Area = usCensusCSV.Water_Area;
        Land_Area = usCensusCSV.Land_Area;
        Population_Density = usCensusCSV.Population_Density;
        Housing_Density = usCensusCSV.Housing_Density;

   }

    public  Object getCensusDTO(CensusAnalyser.Country country) {
        if(country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(StateCode,population,Population_Density,Total_Area);
        return new IndiaCensusCSV(StateCode,population,densityPerSqKm,areaInSqKm);
    }
}