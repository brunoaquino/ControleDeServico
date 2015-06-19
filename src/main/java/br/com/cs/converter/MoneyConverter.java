package br.com.cs.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("br.com.cs.converter.MoneyConverter")
public class MoneyConverter implements Converter {

	final private Locale locale = new Locale("pt", "BR");
	final private DecimalFormat decimalFormat = new DecimalFormat("##0,00", new DecimalFormatSymbols(locale));

	public BigDecimal getAsObject(FacesContext fc, UIComponent component, String value) {

		try {

			decimalFormat.setParseBigDecimal(true);

			return (BigDecimal) decimalFormat.parse(value);
		} catch (ParseException e) {
			throw new ConverterException("Error", e);
		}

	}

	public String getAsString(FacesContext fc, UIComponent component, Object value) {
		DecimalFormat df = new DecimalFormat("###,###,##0.00");
		return df.format(value);
	}
}