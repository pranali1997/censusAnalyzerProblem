package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV extends CensusAnalyser{

    @CsvBindByName(column = "State_Id", required = true)
    public String State_Id;

    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Housing_units", required = true)
    public int Housing_units;

    @CsvBindByName(column = "Total_Area", required = true)
    public double Total_Area;

    @CsvBindByName(column = "Water_Area", required = true)
    public double Water_Area;

    @CsvBindByName(column = "Land_Area", required = true)
    public double Land_Area;

    @CsvBindByName(column = "Population_Density", required = true)
    public double Population_Density;

    @CsvBindByName(column = "Housing_Density", required = true)
    public double Housing_Density;


    public USCensusCSV(String state_Id, int population, double total_Area, double water_Area, double land_Area, double population_Density, double housing_Density) {
        this.State_Id = state_Id;
        this.population = population;
        this.Total_Area = total_Area;
        this.Water_Area = water_Area;
        this.Land_Area = land_Area;
        this.Population_Density = population_Density;
        this.Housing_Density = housing_Density;
    }

    public USCensusCSV() {
    }

    @Override
    public String toString() {
        return "USCensusCSV{" +
                "State_Id='" + State_Id + '\'' +
                ", State='" + State + '\'' +
                ", population=" + population +
                ", Housing_units=" + Housing_units +
                ", Total_Area=" + Total_Area +
                ", Water_Area=" + Water_Area +
                ", Land_Area=" + Land_Area +
                ", Population_Density=" + Population_Density +
                ", Housing_Density=" + Housing_Density +
                '}';
    }
}


