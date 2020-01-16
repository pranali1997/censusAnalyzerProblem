package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV extends CensusAnalyser{

    @CsvBindByName(column = "state_Id", required = true)
    public String state_Id;

    @CsvBindByName(column = "state", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "housing_units", required = true)
    public int housing_units;

    @CsvBindByName(column = "total_Area", required = true)
    public double total_Area;

    @CsvBindByName(column = "water_Area", required = true)
    public double water_Area;

    @CsvBindByName(column = "land_Area", required = true)
    public double land_Area;

    @CsvBindByName(column = "population_Density", required = true)
    public double population_Density;

    @CsvBindByName(column = "housing_Density", required = true)
    public double housing_Density;


    public USCensusCSV(String state_Id, int population, double total_Area, double water_Area, double land_Area, double population_Density, double housing_Density) {
        this.state_Id = state_Id;
        this.population = population;
        this.total_Area = total_Area;
        this.water_Area = water_Area;
        this.land_Area = land_Area;
        this.population_Density = population_Density;
        this.housing_Density = housing_Density;
    }

    public USCensusCSV() {
    }

    @Override
    public String toString() {
        return "USCensusCSV{" +
                "state_Id='" + state_Id + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housing_units=" + housing_units +
                ", total_Area=" + total_Area +
                ", water_Area=" + water_Area +
                ", land_Area=" + land_Area +
                ", population_Density=" + population_Density +
                ", housing_Density=" + housing_Density +
                '}';
    }
}


