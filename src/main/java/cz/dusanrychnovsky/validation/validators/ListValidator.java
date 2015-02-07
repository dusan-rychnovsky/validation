/*
* Copyright (c) 2015 by Casenet, LLC
*
* This file is protected by Federal Copyright Law, with all rights
* reserved. No part of this file may be reproduced, stored in a
* retrieval system, translated, transcribed, or transmitted, in any
* form, or by any means manual, electric, electronic, mechanical,
* electro-magnetic, chemical, optical, or otherwise, without prior
* explicit written permission from Casenet, LLC.
*/
package cz.dusanrychnovsky.validation.validators;

import cz.dusanrychnovsky.validation.Errors;
import cz.dusanrychnovsky.validation.validators.Validator;

import java.util.List;

public class ListValidator<T> extends Validator<List<T>> {

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
