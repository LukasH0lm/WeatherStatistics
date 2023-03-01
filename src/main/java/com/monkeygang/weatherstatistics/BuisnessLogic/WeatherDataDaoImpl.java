package com.monkeygang.weatherstatistics.BuisnessLogic;

import com.monkeygang.weatherstatistics.ControlObjects.WeatherData;

import java.sql.*;
import java.util.Objects;

public class WeatherDataDaoImpl {


    public WeatherDataDaoImpl() {
    }

    public void addWeatherData(WeatherData weatherData) throws SQLException {

        Connection con = ConnectionSingleton.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM WeatherData;");
        ResultSet rs = ps.executeQuery();

        PreparedStatement ps2 = con.prepareStatement("SELECT * FROM WeatherStation;");
        ResultSet rs2 = ps2.executeQuery();

        boolean isDuplicate = false;

        while (rs2.next()) {
            if (Objects.equals(weatherData.getStation().getStationID(), rs2.getInt("id"))) {
                while (rs.next()) {

                    System.out.println("object date: " + weatherData.getDate());
                    System.out.println("database date: " + rs.getTimestamp("date"));

                    if (Objects.equals(weatherData.getDate(), rs.getTimestamp("date"))) {
                        isDuplicate = true;
                        System.out.println(weatherData.getStation().getStationName() + ": " + weatherData.getDate() + " is already in database");
                        break;
                    }
                }

            }
        }


        if (!isDuplicate) {


            int currentID = 100;
            ResultSet rs4 = ps.executeQuery();

            while (rs4.next()) {
                if (rs4.getInt("id") != currentID) {
                    break;
                }
                currentID++;
            }


            PreparedStatement ps3 = con.prepareStatement("INSERT INTO WeatherData VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");

            System.out.println("date before going into database: " + weatherData.getDate());
            ps3.setInt(1, currentID);
            ps3.setTimestamp(2, weatherData.getDate());
            ps3.setDouble(3, weatherData.getRain());
            ps3.setDouble(4, weatherData.getRainMinutes());
            ps3.setDouble(5, weatherData.getMinTemp());
            ps3.setDouble(6, weatherData.getAvgTemp());
            ps3.setDouble(7, weatherData.getMaxTemp());
            ps3.setDouble(8, weatherData.getSun());
            ps3.setDouble(9, weatherData.getAvgWind());
            ps3.setDouble(10, weatherData.getMaxWindGust());
            ps3.setDouble(11, weatherData.getSkyHeight());
            ps3.setDouble(12, weatherData.getCloudCover());
            ps3.setInt(13, weatherData.getStation().getStationID());


            ps3.executeUpdate();
            System.out.println(weatherData.getStation().getStationName() + ": " + weatherData.getDate() + " " + " has been added to database");
            System.out.println();


        }
    }

/*
    @Override
    public void deletePlayList(Playlist playlist) throws SQLException {


        String SQLSongTitle = "'%s'".formatted(playlist.getName());

        PreparedStatement ps = con.prepareStatement("DELETE FROM SongsInPlaylist WHERE PlaylistID=" + getPlaylistID(playlist) + ";");
        ps.executeUpdate();

        PreparedStatement ps2 = con.prepareStatement("DELETE FROM Playlists WHERE PlaylistName=" + SQLSongTitle + ";");
        ps2.executeUpdate();

    }

    @Override
    public void editPlaylist(Playlist playlist, String newName) throws SQLException {


        String SQLSongTitle = "'%s'".formatted(playlist.getName());

        PreparedStatement ps = con.prepareStatement("UPDATE Playlists SET PlaylistName=" + newName + " WHERE PlaylistName=" + SQLSongTitle + ";");

        ps.executeUpdate();


    }


    @Override
    public LinkedList<Playlist> getAllPlaylists() {
        LinkedList<Playlist> playlists = new LinkedList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Playlists;");
            ResultSet rs = ps.executeQuery();

            Playlist playlist;
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);


                playlist = new Playlist(Integer.parseInt(id), name);

                for (Song song : getPlaylistSongs(playlist)) {
                    playlist.addSong(song);
                }

                playlists.add(playlist);

                System.out.println(playlists);

            }

        } catch (SQLException | UnsupportedTagException | IOException e) {
            System.err.println("can not access records");
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);


        }
        return playlists;
    }

    @Override
    public void updatePlaylists() {

    }

    @Override
    public void deleteSongFromPlaylist(Playlist playlist, Song song) throws SQLException {


        // PreparedStatement ps = con.prepareStatement("DELETE FROM SongsInPlaylist WHERE SongID=" + songDao.getIDFromSong(song)  + ";" );
        PreparedStatement ps = con.prepareStatement("DELETE FROM SongsInPlaylist WHERE SongID=" + songDao.getIDFromSong(song) + " AND PlaylistID=" + getPlaylistID(playlist) + ";");


        ps.executeUpdate();


    }

    @Override
    public void addSongToPlaylist(Playlist playlist, Song song) throws SQLException {


        playlist.addSong(song);

        int songID = songDao.getIDFromSong(song);
        int playlistID = PlaylistDaoImpl.getPlaylistID(playlist);

        PreparedStatement ps = con.prepareStatement("INSERT INTO SongsInPlaylist VALUES (?,?);");

        ps.setInt(1, playlistID);
        ps.setInt(2, songID);

        ps.executeUpdate();


    }

    private static int getPlaylistID(Playlist playlist) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Playlists;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (Objects.equals(playlist.getName(), rs.getString("PlaylistName"))) {
                return rs.getInt("PlaylistID");
            }
        }
        return -1;
    }

    public LinkedList<Song> getPlaylistSongs(Playlist playlist) throws SQLException, InvalidDataException, UnsupportedTagException, IOException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM SongsInPlaylist WHERE PlaylistID=?;");
        ps.setInt(1, getPlaylistID(playlist));
        ResultSet rs = ps.executeQuery();

        LinkedList<Song> resList = new LinkedList<>();

        while (rs.next()) {
            resList.add(songDao.getSongfromID(rs.getInt("SongID")));
        }

        return resList;

    }*/

}