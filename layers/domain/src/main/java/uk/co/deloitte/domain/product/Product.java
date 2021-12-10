package uk.co.deloitte.domain.product;

import uk.co.deloitte.domain.ddd.Aggregate;

/**
 * A membership defines the type of access to the facilities and the price
 */
public class Product implements Aggregate<ProductId> {
    private ProductId id;
    private double cost;
    private String name;

    private Product(final ProductId id) {
        this.id = id;
    }

    public static Product with(ProductId id) {
        return new Product(id);
    }

    @Override
    public ProductId id() {
        return id;
    }

    public Product withCost(final double cost) {
        this.cost = cost;
        return this;
    }

    public double cost() {
        return cost;
    }

    public Product withName(final String name) {
        this.name = name;
        return this;
    }

    public String name() {
        return name;
    }
}
