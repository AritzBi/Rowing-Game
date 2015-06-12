package com.rowing.pojo;

import java.util.List;

public class Trainera {

	private List<Remero> remeros;

	private Patron patron;

	private int potenciaTotal;

	private int energiaTotal;

	private int experienciaTotal;

	private int habilidadBuenaMarTotal;

	private int habilidadMalaMarTotal;

	private static int HABILIDAD_BUENA_MAR_PATRON = 2;
	private static int HABILIDAD_MALA_MAR_PATRON = 5;

	public Trainera() {
		
		calcularPotencialTotal();
		calcularEnergiaTotal();
		calcularExperienciaTotal();
		calcularHabilidadMalaMarTotal();
		calcularHabilidadBuenMarTotal();
	}

	public List<Remero> getRemeros() {
		return remeros;
	}

	public void setRemeros(List<Remero> remeros) {
		this.remeros = remeros;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public void calcularPotencialTotal() {
		potenciaTotal = 0;

		for (Remero remero : getRemeros()) {
			potenciaTotal += remero.getPotencia();
		}
		if (potenciaTotal != 0)
			potenciaTotal = (potenciaTotal / remeros.size());
	}

	public void calcularEnergiaTotal() {
		energiaTotal = 0;

		for (Remero remero : getRemeros()) {
			energiaTotal += remero.getEnergia();
		}

		if (energiaTotal != 0)
			energiaTotal = (energiaTotal / remeros.size());
	}

	public void calcularExperienciaTotal() {
		experienciaTotal = 0;

		for (Remero remero : getRemeros()) {
			experienciaTotal += remero.getExperiencia();
		}
		experienciaTotal += patron.getExperiencia();

		int existePatron = patron != null ? 1 : 0;
		if (experienciaTotal != 0)
			experienciaTotal = (experienciaTotal / (remeros.size() + existePatron));
	}

	public void calcularHabilidadBuenMarTotal() {
		habilidadBuenaMarTotal = 0;

		for (Remero remero : getRemeros()) {
			habilidadBuenaMarTotal += remero.getBuena_mar();
		}
		habilidadBuenaMarTotal += (patron.getBuena_mar() * HABILIDAD_BUENA_MAR_PATRON);

		int existePatron = patron != null ? 1 : 0;
		if (habilidadBuenaMarTotal != 0)
			habilidadBuenaMarTotal = (habilidadBuenaMarTotal / (remeros.size() + existePatron));
	}

	public void calcularHabilidadMalaMarTotal() {
		habilidadMalaMarTotal = 0;

		for (Remero remero : getRemeros()) {
			habilidadMalaMarTotal += remero.getMala_mar();
		}
		habilidadMalaMarTotal += (patron.getMala_mar() * HABILIDAD_MALA_MAR_PATRON);

		int existePatron = patron != null ? 1 : 0;
		if (habilidadMalaMarTotal != 0)
			habilidadMalaMarTotal = (habilidadMalaMarTotal / (remeros.size() + existePatron));
	}

	public int getPotenciaTotal() {
		return potenciaTotal;
	}

	public void setPotenciaTotal(int potenciaTotal) {
		this.potenciaTotal = potenciaTotal;
	}

	public int getEnergiaTotal() {
		return energiaTotal;
	}

	public void setEnergiaTotal(int energiaTotal) {
		this.energiaTotal = energiaTotal;
	}

	public int getExperienciaTotal() {
		return experienciaTotal;
	}

	public void setExperienciaTotal(int experienciaTotal) {
		this.experienciaTotal = experienciaTotal;
	}

	public int getHabilidadBuenaMarTotal() {
		return habilidadBuenaMarTotal;
	}

	public void setHabilidadBuenaMarTotal(int habilidadBuenaMarTotal) {
		this.habilidadBuenaMarTotal = habilidadBuenaMarTotal;
	}

	public int getHabilidadMalaMarTotal() {
		return habilidadMalaMarTotal;
	}

	public void setHabilidadMalaMarTotal(int habilidadMalaMarTotal) {
		this.habilidadMalaMarTotal = habilidadMalaMarTotal;
	}

}
