package com.monkeygang.weatherstatistics.BuisnessLogic;

import com.monkeygang.weatherstatistics.ControlObjects.WeatherStation;

import java.sql.*;
import java.util.Objects;

public class WeatherStationDaoImpl {


    public WeatherStationDaoImpl() {

    }

    public void addWeatherStation(WeatherStation weatherStation) throws SQLException {

        Connection con = ConnectionSingleton.getInstance().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM WeatherStation;");
        ResultSet rs = ps.executeQuery();

        boolean isDuplicate = false;

        while (rs.next()) {
            if (Objects.equals(weatherStation.getStationID(), rs.getInt("id"))) {
                isDuplicate = true;
                System.out.println(weatherStation.getStationName() + " is already in database");
                break;
            }
        }


        if (!isDuplicate) {



                PreparedStatement ps2 = con.prepareStatement("INSERT INTO WeatherStation VALUES (?,?,?,?,?,?,?);");

                ps2.setInt(1, weatherStation.getStationID());
                ps2.setString(2, weatherStation.getStationName());
                ps2.setDouble(3,weatherStation.getHeight());
                ps2.setString(4, (weatherStation.getSetupDateString()));
                ps2.setInt(5, -1);
                ps2.setString(6, weatherStation.getCoordinates());
                ps2.setString(7, weatherStation.getCoordinates());



            ps2.executeUpdate();
                System.out.println(weatherStation.getStationName() + " has been added to database");
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