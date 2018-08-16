package net.osomahe.coinacrobat.api.graph.boundary;

/**
 * Interface for plotting a graph.
 *
 * @author m.tkadlec
 */
public interface Graphs {

    /**
     * By default, X-axis is a timeline, so the value will be plotted as Y-axis value on the current time
     *
     * @param graphName defines the name of the graph. All values with the same name will be plotted to the same graph
     * @param value to be plotted
     */
    void plot(String graphName, Double value);
}
