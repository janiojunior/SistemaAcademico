package br.unitins.sac.validation;

import br.unitins.sac.application.ValidationException;

public interface Validation<T> {
	public void validate(T t) throws ValidationException;
}
