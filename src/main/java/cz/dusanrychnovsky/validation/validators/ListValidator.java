package cz.dusanrychnovsky.validation.validators;

import cz.dusanrychnovsky.validation.Errors;
import cz.dusanrychnovsky.validation.validators.Validator;

import java.util.List;

public class ListValidator<T> implements Validator<List<T>> {

    private final Validator<T> itemValidator;

    /**
     *
     * @param itemValidator
     */
    public ListValidator(Validator<T> itemValidator) {
        this.itemValidator = itemValidator;
    }

    @Override
    public Errors validate(List<T> list) {

        Errors result = new Errors();
        for (int i = 0; i < list.size(); i++) {
            result.addAll("" + i, itemValidator.validate(list.get(i)));
        }

        return result;
    }
}
