package com.monkeygang.weatherstatistics.BuisnessLogic;

import com.monkeygang.weatherstatistics.ControlObjects.Measurement;

import java.sql.*;
import java.util.Objects;

public class WeatherDataDaoImpl {


    public WeatherDataDaoImpl() {
    }

    public void addWeatherData(Measurement measurement) throws SQLException {

        Connection con = ConnectionSingleton.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM measurement;");
        ResultSet rs = ps.executeQuery();

        PreparedStatement ps2 = con.prepareStatement("SELECT * FROM WeatherStation;");
        ResultSet rs2 = ps2.executeQuery();

        boolean isDuplicate = false;

        while (rs2.next()) {
            if (Objects.equals(measurement.getStation().getStationID(), rs2.getInt("id"))) {
                while (rs.next()) {

                    System.out.println("object date: " + measurement.getDate());
                    System.out.println("database date: " + rs.getTimestamp("date_time"));

                    if (Objects.equals(measurement.getDate(), rs.getTimestamp("date_time"))) {
                        isDuplicate = true;
                        System.out.println(measurement.getStation().getStationName() + ": " + measurement.getDate() + " is already in database");
                        break;
                    }
                }

            }
        }


        if (!isDuplicate) {


            int measurement_id = 100;
            ResultSet rs4 = ps.executeQuery();

            while (rs4.next()) {
                if (rs4.getInt("id") != measurement_id) {
                    break;
                }
                measurement_id++;
            }


            PreparedStatement ps_measurement = con.prepareStatement("INSERT INTO measurement VALUES (?,?,?);");

            System.out.println("date before going into database: " + measurement.getDate());
            ps_measurement.setInt(1, measurement.getStation().getStationID());
            ps_measurement.setInt(2, measurement_id);
            ps_measurement.setTimestamp(3, measurement.getDate());


            ps_measurement.executeUpdate();

            PreparedStatement ps_rain_data = con.prepareStatement("INSERT INTO rain_data VALUES (?,?,?);");

            ps_rain_data.setInt(1, measurement_id);
            ps_rain_data.setDouble(2, measurement.getRain());
            ps_rain_data.setDouble(3, measurement.getRainMinutes());

            ps_rain_data.executeUpdate();


            PreparedStatement ps_temp_data = con.prepareStatement("INSERT INTO temp_data VALUES (?,?,?,?);");

            ps_temp_data.setInt(1, measurement_id);
            ps_temp_data.setDouble(2, measurement.getMinTemp());
            ps_temp_data.setDouble(3, measurement.getAvgTemp());
            ps_temp_data.setDouble(4, measurement.getMaxTemp());

            ps_temp_data.executeUpdate();


            PreparedStatement ps_sun_data = con.prepareStatement("INSERT INTO sun_data VALUES (?,?);");
            ps_sun_data.setInt(1, measurement_id);
            ps_sun_data.setDouble(2, measurement.getSun());

            ps_sun_data.executeUpdate();


            PreparedStatement ps_wind_data = con.prepareStatement("INSERT INTO wind_data VALUES (?,?,?);");
            ps_wind_data.setInt(1, measurement_id);
            ps_wind_data.setDouble(2, measurement.getAvgWind());
            ps_wind_data.setDouble(3, measurement.getMaxWindGust());

            ps_wind_data.executeUpdate();


            PreparedStatement ps_sky_data = con.prepareStatement("INSERT INTO sky_data VALUES (?,?);");
            ps_sky_data.setInt(1, measurement_id);
            ps_sky_data.setDouble(2, measurement.getSkyHeight());

            ps_sky_data.executeUpdate();


            PreparedStatement ps_cloud_data = con.prepareStatement("INSERT INTO cloud_data VALUES (?,?);");
            ps_cloud_data.setInt(1, measurement_id);
            ps_cloud_data.setDouble(2, measurement.getCloudCover());

            ps_cloud_data.executeUpdate();





            System.out.println(measurement.getStation().getStationName() + ": " + measurement.getDate() + " " + " has been added to database");
            System.out.println();


        }
    }


}