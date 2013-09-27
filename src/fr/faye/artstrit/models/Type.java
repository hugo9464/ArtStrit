package fr.faye.artstrit.models;


public enum Type {
	SPORT("Sport"),
	MUSIC("Musique"),
	COMEDY("Theatre"),
	DANCE("Danse"),
	STATUE("Statue humaine");
	
	@SuppressWarnings("unused")
	private String value;
	
	Type(String value)	{
		this.value=value;
	}
	
}
