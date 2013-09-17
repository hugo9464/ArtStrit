package fr.faye.artstrit.models;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseObject;

public class Artist {
	private String sex;
	private int rate;
	private String comment;
	private LatLng loc;
	private String name;

	public static final String SEX = "sex";
	public static final String RATE = "rate";
	public static final String COMMENT = "comment";
	public static final String LAT = "lat";
	public static final String LNG = "lng";
	public static final String NAME = "name";

	public static final String TYPE = "type";
	
	public Artist(ParseObject artist) {
		this.sex = artist.getString(SEX);
		this.rate = artist.getInt(RATE);
		this.comment = artist.getString(COMMENT);
		this.loc = new LatLng(artist.getDouble(LAT), artist.getDouble(LNG));
		this.name = artist.getString(NAME);

	}

	public String sex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int rate() {
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

	@Override
	public String toString() {
		return "Artist : sex=" + sex + ", rate=" + rate + ", comment=" + comment
				+ ", loc=" + loc + ", name=" + name;
	}
}
