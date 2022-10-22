package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

/**
 * @author kany
 */
public abstract class BaseId<T> {
    private final T id;

    public BaseId(T value) {
        this.id = value;
    }

    public T getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(id, baseId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
