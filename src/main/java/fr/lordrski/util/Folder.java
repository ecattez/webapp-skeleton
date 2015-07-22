package fr.lordrski.util;

public enum Folder {
	
	EXCHANGE, FICTABLES;
	
	@Override
	public String toString() {
		return super.name().toLowerCase();
	}

}
