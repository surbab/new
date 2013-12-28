package com.erp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("AlphaNumericValidator")
public class AlphaNumericValidator implements Validator{
 
	private static final String ALPHNUMERIC_PATTERN = "^[-\\w\\s]+$";
 
	private Pattern pattern;
	private Matcher matcher;

	public AlphaNumericValidator(){
		  pattern = Pattern.compile(ALPHNUMERIC_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){

			FacesMessage msg = 
				new FacesMessage("Alpha Numeric validation failed.", 
						"Only Alphabets and Numbers!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}

}
