//id:260800991
//name:Antoine Hamasaki-Belanger

package assignment4;

import java.util.ArrayList;

public class MusicStore {

    // Initializing field variables
    private MyHashTable<String, Song> hashTitle;
    private MyHashTable<String, ArrayList<Song>> hashArtist;
    private MyHashTable<Integer, ArrayList<Song>> hashYear;

   
    public MusicStore(ArrayList<Song> songs) {
        //adding songs to hashTitle
    	hashTitle = new MyHashTable<String, Song>(songs.size());
        for(Song song: songs) {
        	hashTitle.put(song.getTitle(), song);
        }

        //adding songs to hashArtist
        hashArtist = new MyHashTable<String, ArrayList<Song>>(songs.size());
        //iterate through all songs
        for(Song song: songs) {
            String artist = song.getArtist();
            
            //not 1 song from specific artist yet
            if((hashArtist.get(artist) == null) || (hashArtist.get(artist).isEmpty())) {
            	//create list for songs of artist
                ArrayList<Song> list = new ArrayList<Song>();
                //add first song from artist encountered
                list.add(song);
                hashArtist.put(artist, list);
            } 
            //already have songs from specific artist
            else 
            	hashArtist.get(artist).add(song);
        }
        
        //adding songs to hashYear
        hashYear = new MyHashTable<Integer, ArrayList<Song>>(songs.size());
        //iterate through all songs
        for(Song song: songs) {
            Integer year = song.getYear();
            
            //not 1 song from specific artist yet
            if((hashYear.get(year) == null) || (hashYear.get(year).isEmpty())) {
            	//create list for songs of year
            	ArrayList<Song> list = new ArrayList<Song>();
                //add first song from year encountered
                list.add(song);
                hashYear.put(year, list);
            } 
            //already have songs from specific year
            else 
                hashYear.get(year).add(song);
        }
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        String title = s.getTitle();
        String artist = s.getArtist();
        Integer year = s.getYear();

        //add song to hashTitle
        hashTitle.put(title, s);

        //No song from artist and year 
        if ((hashArtist.get(artist).isEmpty() || hashArtist.get(artist) == null) && (hashYear.get(year).isEmpty() || hashYear.get(year) == null)) {
        	//create new arraylists 
            ArrayList<Song> listArtist = new ArrayList<Song>();
            ArrayList<Song> listYear = new ArrayList<Song>();
            //add song in each list
            listArtist.add(s);
            listYear.add(s);
            //add song to each hash
            hashArtist.put(artist, listArtist);
            hashYear.put(year, listYear);
        }
        //song exists from artist but not year
        else if ((!hashArtist.get(artist).isEmpty() ||hashArtist.get(artist) != null) && (hashYear.get(year).isEmpty() || hashYear.get(year) == null)) {
            ArrayList<Song> addedYear = new ArrayList<>();
            addedYear.add(s);
            hashArtist.get(artist).add(s);
            hashYear.put(year, addedYear);
        }
        //no song from this artist but exists for this year
        else if ((hashArtist.get(artist).isEmpty() || hashArtist.get(artist) == null) && (!hashYear.get(year).isEmpty() || hashYear.get(year) != null )) {
            ArrayList<Song> listArtist = new ArrayList<Song>();
            listArtist.add(s);
            hashYear.get(year).add(s);
            hashArtist.put(artist, listArtist);
        }
        //song exists from artist and year
        else {
        	hashArtist.get(artist).add(s);
            hashYear.get(year).add(s);
        }
    }

    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
       return hashTitle.get(title);
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        return hashArtist.get(artist);
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        return hashYear.get(year);
    }
}
