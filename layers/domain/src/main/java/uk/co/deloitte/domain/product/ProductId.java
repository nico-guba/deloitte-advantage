package uk.co.deloitte.domain.product;

import uk.co.deloitte.domain.ddd.Identity;

public class ProductId extends Identity<String> {

    private ProductId(final String value) {
        super(value);
    }

    public static ProductId with(String value) {
        return new ProductId(value);
    }
}
