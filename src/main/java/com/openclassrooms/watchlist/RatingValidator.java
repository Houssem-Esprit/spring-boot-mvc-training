package com.openclassrooms.watchlist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<Rating, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		Double number;
		
		try {
			number = Double.parseDouble(value);

		} catch (NumberFormatException e) {
			return false;
		}
		
		
		if(number < 1 || number > 10) {
			return false;
		}
		
		return true;
	}

}
