package com.admin.catalog.domain.category;

import com.admin.catalog.domain.exception.DomainException;
import com.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var atualCategory =
                Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        Assertions.assertNotNull(atualCategory);
        Assertions.assertNotNull(atualCategory.getId());
        Assertions.assertEquals(expectedName, atualCategory.getName());
        Assertions.assertEquals(expectedDescription, atualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, atualCategory.isActive());
        Assertions.assertNotNull(atualCategory.getCreatedAt());
        Assertions.assertNotNull(atualCategory.getUpdatedAt());
        Assertions.assertNull(atualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceivedError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var actualCategory =
                Category.newCategory(null,expectedDescription,expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceivedError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory =
                Category.newCategory("  ",expectedDescription,expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceivedError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must between 3 and 255 characters";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var actualCategory =
                Category.newCategory("Fi  ",expectedDescription,expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceivedError() {
        final var expectedName = """
                Por conseguinte, o início da atividade geral de formação de atitudes ainda não
                demonstrou convincentemente que
                vai participar na mudança do sistema de participação geral.
                Por conseguinte, o início da atividade geral de formação de atitudes ainda não
                demonstrou convincentemente que
                vai participar na mudança do sistema de participação geral.
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must between 3 and 255 characters";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;


        final var actualCategory =
                Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldNotReceivedError() {
        final var expectedName = "Filmes";
        final var expectedDescription = "   ";
        final var expectedIsActive = true;


        final var atualCategory =
                Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        Assertions.assertDoesNotThrow(() -> atualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(atualCategory);
        Assertions.assertNotNull(atualCategory.getId());
        Assertions.assertEquals(expectedName, atualCategory.getName());
        Assertions.assertEquals(expectedDescription, atualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, atualCategory.isActive());
        Assertions.assertNotNull(atualCategory.getCreatedAt());
        Assertions.assertNotNull(atualCategory.getUpdatedAt());
        Assertions.assertNull(atualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldNotReceivedError() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A Categoria mais assitida";
        final var expectedIsActive = false;


        final var atualCategory =
                Category.newCategory(expectedName,expectedDescription,expectedIsActive);

        Assertions.assertDoesNotThrow(() -> atualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(atualCategory);
        Assertions.assertNotNull(atualCategory.getId());
        Assertions.assertEquals(expectedName, atualCategory.getName());
        Assertions.assertEquals(expectedDescription, atualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, atualCategory.isActive());
        Assertions.assertNotNull(atualCategory.getCreatedAt());
        Assertions.assertNotNull(atualCategory.getUpdatedAt());
        Assertions.assertNotNull(atualCategory.getDeletedAt());
    }
}