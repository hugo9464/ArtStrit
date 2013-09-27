package fr.faye.artstrit.models;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

public class Artist {
	
	private Artists artists;
	
	private double rate;
	private String comment;
	private LatLng loc;
	private String name;
	private String type;
	private Bitmap picture;

	public static final String RATE = "rate";
	public static final String COMMENT = "comment";
	public static final String LAT = "lat";
	public static final String LNG = "lng";
	public static final String NAME = "name";
	public static final String PICTURE = "picture";

	public static final String SPORT = "Sport";
	public static final String DANCE = "Danse";
	public static final String THEATRE = "ThŽ‰tre";
	public static final String STATUE = "Statue humaine";
	public static final String MUSIC = "Musique";
	public static final String MAGIC = "Magie";
	


	public static final String TYPE = "type";
	
	public Artist(float rate, String comment, String name, LatLng loc, String type){
	
		this.artists = Artists.getInstance();
		
		this.rate = rate; 
		this.comment = comment;
		this.loc = loc;
		this.name = name;
		this.type = type;
		if(artists.current_picture()!=null)
			this.picture = artists.current_picture();
		sendToParse();
	}
	


	public Artist(ParseObject artist) throws ParseException  {
		
		this.rate = artist.getDouble(RATE);
		this.comment = artist.getString(COMMENT);
		this.loc = new LatLng(artist.getDouble(LAT), artist.getDouble(LNG));
		this.name = artist.getString(NAME);
		this.type = artist.getString(TYPE);
		if(artist.getParseFile(PICTURE)!=null)
			getBitmapFromParse(artist.getParseFile(PICTURE));
		
	}

	private void getBitmapFromParse(ParseFile file) throws ParseException{
		byte[] picture_array = file.getData();
		this.setPicture(BitmapFactory.decodeByteArray(picture_array, 0, picture_array.length));
	}
	public String name(){
		return name;
	}


	public double rate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String comment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LatLng loc() {
		return loc;
	}

	public void setLoc(LatLng loc) {
		this.loc = loc;
	}

	public String type() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private void sendToParse() {
		 ParseObject newArtist = new ParseObject("artists");
		 newArtist.put(Artist.LAT, loc.latitude);
		 newArtist.put(Artist.LNG, loc.longitude);
		 newArtist.put(Artist.COMMENT, comment);
		 newArtist.put(Artist.NAME, name);
		 newArtist.put(Artist.RATE, rate);
		 newArtist.put(Artist.TYPE, type);
		 if(artists.current_picture()!=null){
			 ByteArrayOutputStream stream = new ByteArrayOutputStream();
			 Bitmap newBitmap = artists.current_picture();
			 newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			 byte[] data = stream.toByteArray();
			 
			 ParseFile file = new ParseFile("resume.txt", data);
			 file.saveInBackground();
			 
			 newArtist.put(Artist.PICTURE, file);

		 }
		 

		 newArtist.saveInBackground();
	}
	
	@Override
	public String toString() {
		return "Artist : rate=" + rate + ", comment=" + comment
				+ ", loc=" + loc + ", name=" + name + ", type=" + type;
	}



	public Bitmap picture() {
		return picture;
	}



	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}
}
