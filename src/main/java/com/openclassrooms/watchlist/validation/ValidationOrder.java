package com.openclassrooms.watchlist.validation;

import javax.validation.GroupSequence;

@GroupSequence(value = {FieldValidations.class, ClassValidations.class})
public interface ValidationOrder {
	
}
