package study.small.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import study.small.entity.Product;

public interface ProductESDAO extends ElasticsearchRepository<Product,Integer> {


}
