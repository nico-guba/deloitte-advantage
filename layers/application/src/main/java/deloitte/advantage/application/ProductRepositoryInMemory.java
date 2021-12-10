package deloitte.advantage.application;

import uk.co.deloitte.domain.product.Product;
import uk.co.deloitte.domain.product.ProductId;
import uk.co.deloitte.domain.product.ProductRepository;

public class ProductRepositoryInMemory extends InMemoryRepository<ProductId, Product> implements ProductRepository {
}
