package com.duongkk.unitconverter.libs;

public class Unit {
	private String name;
	private double n;
	
	
	public Unit(String name, double n) {
		this.name = name;
		this.n = n;
	}

	public String getName() {
		return name;
	}

	public double getN() {
		return n;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
