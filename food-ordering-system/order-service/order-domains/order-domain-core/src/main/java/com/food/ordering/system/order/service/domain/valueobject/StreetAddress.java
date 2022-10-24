package com.food.ordering.system.order.service.domain.valueobject;

import com.food.ordering.system.domain.valueobject.BaseId;

import java.util.Objects;
import java.util.UUID;

public class StreetAddress extends BaseId<UUID> {
    private final String street;
    private final String postalCode;
    private final String city;

    public StreetAddress(UUID id, String street, String postalCode, String city) {
        super(id);
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    private StreetAddress(Builder builder) {
        super(builder.id);
        street = builder.street;
        postalCode = builder.postalCode;
        city = builder.city;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public UUID getValue() {
        return super.getValue();
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StreetAddress that = (StreetAddress) o;
        return Objects.equals(street, that.street) && Objects.equals(postalCode, that.postalCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, postalCode, city);
    }


    public static final class Builder {
        private UUID id;
        private String street;
        private String postalCode;
        private String city;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(UUID id) {
            id = id;
            return this;
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder postalCode(String val) {
            postalCode = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public StreetAddress build() {
            return new StreetAddress(this);
        }
    }
}
