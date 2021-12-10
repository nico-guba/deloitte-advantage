package uk.co.deloitte.domain.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    Product product = Product.with(ProductId.with("gym"));

    @Test
    void hasId() {
        assertThat(product.id()).isNotNull();
    }

    @Test
    void hasCost() {
        assertThat(product.withCost(12.34).cost()).isEqualTo(12.34);
    }

    @Test
    void hasName() {
        assertThat(product.withName("Gym Membership").name()).isEqualTo("Gym Membership");
    }
}