package uk.co.deloitte.domain.product;

import uk.co.deloitte.domain.ddd.support.IdentityVerifier;

class ProductIdTest implements IdentityVerifier<ProductId> {

    @Override
    public ProductId invalidInstance() {
        return ProductId.with(null);
    }

    @Override
    public ProductId validInstance() {
        return ProductId.with("full");
    }
}