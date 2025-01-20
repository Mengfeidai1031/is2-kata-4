package software.ulpgc.architecture.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Barchart {
    private final Map<String, Integer> barchart;
    private final String title;
    private final String xAxis;
    private final String yAxis;

    public Barchart(String title, String xAxis, String yAxis) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.barchart = new HashMap<>();
    }

    public int get(String category) {
        return barchart.get(category);
    }

    public void put(String category, int value) {
        barchart.put(category, value);
    }

    public Set<String> keySet() {
        return barchart.keySet();
    }

    public String title() {
        return title;
    }

    public String xAxis() {
        return xAxis;
    }

    public String yAxis() {
        return yAxis;
    }

    public Integer getOrDefault(String key, int defaultValue) {
        return barchart.getOrDefault(key, defaultValue);
    }
}
