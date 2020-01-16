package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV extends CensusAnalyser{

    @CsvBindByName(column = "state", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int densityPerSqKm;

    public IndiaCensusCSV(String state, int population, int densityPerSqKm, int total_area) {
        this.population=population;
        this.areaInSqKm=total_area;
        this.densityPerSqKm=densityPerSqKm;
        this.state=state;
    }

    public IndiaCensusCSV() {

    }

    @Override
    public String toString() {
        return "{" +
                "state='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqKm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}
