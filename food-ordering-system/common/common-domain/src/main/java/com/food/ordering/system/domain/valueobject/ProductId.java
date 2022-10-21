package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * @author kany
 */
public class ProductId extends BaseId<UUID> {
    public ProductId(UUID value) {
        super(value);
    }
}
