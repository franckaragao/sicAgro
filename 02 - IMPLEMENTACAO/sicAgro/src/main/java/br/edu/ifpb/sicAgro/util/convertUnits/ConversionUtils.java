package br.edu.ifpb.sicAgro.util.convertUnits;


import java.math.BigDecimal;

import br.edu.ifpb.sicAgro.enumerations.MeasurementType;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;

/**
 * Classe responsável por válidar opções de conversões de medidas 
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 * 
 */
public class ConversionUtils {

	/**
	 * 
	 * @param mType
	 * @param toConverter
	 * @param value
	 * @return
	 * @throws SicAgroExceptionHandler
	 */
	public static Double convert(MeasurementType toConvert,MeasurementType mType, BigDecimal value) throws SicAgroExceptionHandler {

		if (mType.equals(MeasurementType.KLG)) {
			return optionsConvertFromKLG(toConvert, value.doubleValue());

		} else if (mType.equals(MeasurementType.GRAMA)) {
			return optionsConvertFromGram(toConvert, value.doubleValue());
			
		} else if(mType.equals(MeasurementType.SACA)){
			return optionsConvertFromSaca(toConvert, value.doubleValue());
			
		} else if(mType.equals(MeasurementType.TONELADA)){
			return optionsConvertFromKlgToTonne(toConvert, value.doubleValue());
			
		}else{
			throw new SicAgroExceptionHandler("Medida é inválida para o item selecionado");
		}
	}

	/**
	 * 
	 * @param toConvert
	 * @param value
	 * @return
	 * @throws SicAgroExceptionHandler
	 */
	private static Double optionsConvertFromKLG(MeasurementType toConvert, Double value) throws SicAgroExceptionHandler {
		if (toConvert.equals(MeasurementType.GRAMA)) {
			return conversionFromGramToKg(value);
			
		} else {
			throw new SicAgroExceptionHandler(
					"Medida informada é invalida! A medida correspondente deve ser: "
							+ MeasurementType.GRAMA.getMeasurement());
		}
	}
	
	/**
	 * 
	 * @param toConvert
	 * @param value
	 * @return
	 * @throws SicAgroExceptionHandler
	 */
	private static Double optionsConvertFromGram(MeasurementType toConvert, Double value) throws SicAgroExceptionHandler{
		if (toConvert.equals(MeasurementType.KLG)) {
			return conversionFromKgToGram(value);
			
		} else {
			throw new SicAgroExceptionHandler(
					"Medida informada é invalida! A medida correspondente deve ser: "
							+ MeasurementType.KLG.getMeasurement());
		}
	}
	
	/**
	 * 
	 * @param toConvert
	 * @param value
	 * @return
	 * @throws SicAgroExceptionHandler
	 */
	private static Double optionsConvertFromSaca(MeasurementType toConvert, Double value) throws SicAgroExceptionHandler{
		if(toConvert.equals(MeasurementType.KLG)){
			return convesionFromKlgToSaca(value);
			
		}else{
			throw new SicAgroExceptionHandler(
					"Medida informada é invalida! A medida correspondente deve ser: "
							+ MeasurementType.KLG.getMeasurement() +" ou "+MeasurementType.SACA);
		}
	}
	
	/**
	 * 
	 * @param toConvert
	 * @param value
	 * @return
	 * @throws SicAgroExceptionHandler
	 */
	private static Double optionsConvertFromKlgToTonne(MeasurementType toConvert, Double value) throws SicAgroExceptionHandler{
		if(toConvert.equals(MeasurementType.KLG)){
			return conversionFromKgToTonne(value);
			
		}else{
			throw new SicAgroExceptionHandler(
					"Medida informada é invalida! A medida correspondente deve ser: "
							+ MeasurementType.KLG.getMeasurement() +" ou "+MeasurementType.TONELADA.getMeasurement());
		}
	}

	/**
	 * 
	 * @param valueInKg
	 * @return
	 */
	private static double conversionFromKgToGram(Double valueInKg) {
		return valueInKg * 1000;
	}

	/**
	 * 
	 * @param valueGram
	 * @return
	 */
	private static double conversionFromGramToKg(Double valueGram) {
		return valueGram / 1000;
	}

	/**
	 * 
	 * @param valueSaca
	 * @return
	 */
	private static double convesionFromKlgToSaca(double valueKlg) {
		return valueKlg / 60;
	}

	/**
	 * 
	 * @param valueKg
	 * @return
	 * @throws SicAgroExceptionHandler
	 */
	private static double conversionFromKgToTonne(Double valueKg) throws SicAgroExceptionHandler {
		return valueKg / 1000;
	}
}
