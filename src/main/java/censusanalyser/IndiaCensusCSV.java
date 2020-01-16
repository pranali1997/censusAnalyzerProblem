package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV extends CensusAnalyser{

    public IndiaCensusCSV() {
    }

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int densityPerSqKm;

    public IndiaCensusCSV(String stateCode, int population, int population_density, int total_area) {
        this.population=population;
        this.areaInSqKm=total_area;
        this.densityPerSqKm=population_density;
        this.state=stateCode;
    }

    @Override
    public String toString() {
        return "{" +
                "State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqKm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}
