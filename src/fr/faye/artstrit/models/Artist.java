package fr.faye.artstrit.models;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseObject;

public class Artist {
	private double rate;
	private String comment;
	private LatLng loc;
	private String name;
	private String type;

	public static final String RATE = "rate";
	public static final String COMMENT = "comment";
	public static final String LAT = "lat";
	public static final String LNG = "lng";
	public static final String NAME = "name";

	public static final String TYPE = "type";
	
	public Artist(float rate, String comment, String name, LatLng loc, String type){
	
		this.rate = rate; 
		this.comment = comment;
		this.loc = loc;
		this.name = name;
		this.type = type;
		
		sendToParse();
	}
	


	public Artist(ParseObject artist) {
		
		this.rate = artist.getDouble(RATE);
		this.comment = artist.getString(COMMENT);
		this.loc = new LatLng(artist.getDouble(LAT), artist.getDouble(LNG));
		this.name = artist.getString(NAME);
		this.type = artist.getString(TYPE);

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


		 newArtist.saveInBackground();
	}
	
	@Override
	public String toString() {
		return "Artist : rate=" + rate + ", comment=" + comment
				+ ", loc=" + loc + ", name=" + name + ", type=" + type;
	}
}
