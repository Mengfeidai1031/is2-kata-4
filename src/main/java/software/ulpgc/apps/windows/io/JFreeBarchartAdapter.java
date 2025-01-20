package software.ulpgc.apps.windows.io;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.architecture.model.Barchart;

public class JFreeBarchartAdapter {
    public static JFreeChart adapt(Barchart barchart) {
        return ChartFactory.createBarChart(
                barchart.title(),
                barchart.xAxis(),
                barchart.yAxis(),
                datasetOf(barchart)
        );
    }

    private static CategoryDataset datasetOf(Barchart barchart) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String key : barchart.keySet()) dataset.addValue(barchart.get(key), "", key);
        return dataset;
    }
}
