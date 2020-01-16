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

    public String stateCode;
    public int housing_units;
    public double total_Area;
    public double water_Area;
    public double land_Area;
    public double population_Density;
    public double housing_Density;

    public CensusDAO(USCensusCSV usCensusCSV) {
        stateCode = usCensusCSV.state_Id;
        state = usCensusCSV.state;
        population = usCensusCSV.population;
        housing_units = usCensusCSV.housing_units;
        total_Area = usCensusCSV.total_Area;
        water_Area = usCensusCSV.water_Area;
        land_Area = usCensusCSV.land_Area;
        population_Density = usCensusCSV.population_Density;
        housing_Density = usCensusCSV.housing_Density;

   }

    public  Object getCensusDTO(CensusAnalyser.Country country) {
        if(country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(stateCode, housing_units, total_Area, water_Area, land_Area, population_Density, housing_Density);
        return new IndiaCensusCSV(state,population,densityPerSqKm,areaInSqKm);
    }

}