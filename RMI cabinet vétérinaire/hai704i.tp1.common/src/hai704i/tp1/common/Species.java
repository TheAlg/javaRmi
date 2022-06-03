package hai704i.tp1.common;

import java.io.Serializable;

public class Species implements Serializable {
	/* ATTRIBUTES */
	private String name;
	private int averageLife;
	
	/* CONSTRUCTORS */
	public Species() {
		
	}
	
	public Species(String name, int averageLife) {
		this.name = name;
		this.averageLife = averageLife;
	}
	
	/* METHODS */
	public String getName() {
		return name;
	}
	
	public int getAverageLife() {
		return averageLife;
	}
	
	@Override
	public String toString() {
		return "(Name: "+name+", Average Life: "+averageLife+")";
	}
}
