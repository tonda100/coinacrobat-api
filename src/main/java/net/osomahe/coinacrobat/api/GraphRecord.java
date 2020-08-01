package net.osomahe.coinacrobat.api;

/**
 * @author Antonin Stoklasek
 */
public class GraphRecord {

    private final String name;

    private final Double value;

    public GraphRecord(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "GraphRecord{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
