package com.admin.catalog.domain.category;

import com.admin.catalog.domain.AggregateRoot;
import com.admin.catalog.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> {

    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryID anId,
            final String aName,
            final String aDescription,
            final boolean isActive,
            final Instant aCreatedAt,
            final Instant aUpdatedAt,
            final Instant aDeletedAt
    ) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = aCreatedAt;
        this.updatedAt = aUpdatedAt;
        this.deletedAt = aDeletedAt;
    }

    public static Category newCategory(
            final String aName,
            final String aDescription,
            final boolean aIsActive
    ) {
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deletedAt = aIsActive ? null : now;

        return new Category(id, aName, aDescription, aIsActive, now, now, deletedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public Category deactivate() {
        final var now = Instant.now();
        if(getDeletedAt() == null){
            this.deletedAt = now;
        }
        this.active = false;
        this.updatedAt = now;

        return  this;
    }

    public Category activate() {
        if(!this.active){
            this.deletedAt = null;
            this.updatedAt = Instant.now();
            this.active = true;
        }
        return  this;
    }

    public Category update(
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        if(isActive){
            activate();
        } else {
            deactivate();
        }

        this.name = aName;
        this.description = aDescription;
        this.updatedAt = Instant.now();

        return this;
    }
}