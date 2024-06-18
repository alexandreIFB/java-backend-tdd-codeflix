package com.admin.catalog.domain.category;

import com.admin.catalog.domain.validation.Error;
import com.admin.catalog.domain.validation.ValidationHandler;
import com.admin.catalog.domain.validation.Validator;

public class CategoryValidator  extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory,final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int nameLength = name.trim().length();
        if (nameLength < 3 || nameLength > 255) {
            this.validationHandler().append(new Error("'name' must between 3 and 255 characters"));
        }
    }
}
