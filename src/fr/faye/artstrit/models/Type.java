package fr.faye.artstrit.models;

public enum Type {
	SPORT("sport"),
	MUSIC("music"),
	COMEDY("comedy"),
	DANCE("dance"),
	STATUE("statue");
	
	@SuppressWarnings("unused")
	private String value;
	
	Type(String value)	{
		this.value=value;
	}
	
}
