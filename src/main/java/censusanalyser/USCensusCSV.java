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


