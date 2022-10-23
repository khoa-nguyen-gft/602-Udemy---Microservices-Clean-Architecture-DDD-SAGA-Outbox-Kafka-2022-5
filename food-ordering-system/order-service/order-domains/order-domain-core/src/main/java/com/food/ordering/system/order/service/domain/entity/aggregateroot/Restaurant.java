package com.food.ordering.system.order.service.domain.entity.aggregateroot;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;

import java.util.List;

/**
 * @author kany
 */
public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private boolean active;

    private Restaurant(Builder builder) {
        super.setId(builder.id);
        products = builder.products;
        active = builder.active;
    }

    public void validateRestaurantIsActive(){
        if(!isActive()){
            throw new OrderDomainException("Restaurant with Id " + this.getId().getValue() + " is currently not active");
        }
    }

    private boolean isActive() {
        return active == true;
    }

    public List<Product> getProducts() {
        return products;
    }

    public static final class Builder {
        private RestaurantId id;
        private List<Product> products;
        private boolean active;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(RestaurantId val) {
            id = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
