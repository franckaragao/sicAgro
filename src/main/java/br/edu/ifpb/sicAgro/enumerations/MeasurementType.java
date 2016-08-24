package br.edu.ifpb.sicAgro.enumerations;

public enum MeasurementType {

	LITRO("Litro(s)"), GRAMA("Grama(s)"), UNIDADE("Unidade(s)"), SACA("Saca(s)"), METRO(
			"Metro(s)"), KLG("Quilo(s)"), TONELADA("Tonelada(s)");

	private String measurement;

	private MeasurementType(String measurement) {
		this.measurement = measurement;
	}

	public String getMeasurement() {
		return measurement;
	}

}
