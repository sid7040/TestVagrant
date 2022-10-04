package com.testvagrant.model.comparator;

import com.testvagrant.config.PropertyFileReader;
import com.testvagrant.model.WeatherInfo;

import java.io.File;
import java.util.Comparator;
import java.util.Properties;

import static java.lang.String.format;

public class TemperatureComparator implements Comparator<WeatherInfo> {
    private final Properties test_data = new PropertyFileReader(new File(format("%s/src/test/resources/test_data/data.properties",
            System.getProperty("user.dir")))).getPropertyFile();

    @Override
    public int compare(WeatherInfo o1, WeatherInfo o2) {
        float temp1 = o1.getTempDegrees();
        float temp2 = o2.getTempDegrees();
        float tempVariance = Float.parseFloat(test_data.getProperty("tempVariance"));

        float absTemp = Math.abs(temp1 - temp2);

        return (absTemp >= 0 && absTemp <= tempVariance) ? 0 : 1;
    }
}
