package study.small.service.impl;

import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import study.small.entity.Category;
import study.small.entity.OrderItem;
import study.small.entity.Product;
import study.small.es.ProductESDAO;
import study.small.mapper.ProductMapper;
import study.small.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductESDAO productESDAO;

    @Override
    public void add(Product product) {
        productMapper.add(product);
        productESDAO.save(product);

    }

    @Override
    public void delete(int id) {
        productMapper.delete(id);
        productESDAO.deleteById(id);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
        productESDAO.save(product);
    }

    @Override
    public Product get(int id) {
        Product p = productMapper.get(id);
        setCategory(p);
        return p;
    }

    @Override
    public List<Product> queryAll() {
        List<Product> ps = productMapper.queryAll();
        setCategory(ps);
        return ps;
    }

    @Override
    public List<Product> queryByCid(int cid) {
        List<Product> ps = productMapper.queryByCid(cid);
        setCategory(ps);
        return ps;
    }

    @Override
    public void fill(Category c) {
        List<Product> ps = queryByCid(c.getId());
        productImageService.setFirstProductImage(ps);
        c.setProducts(ps);
    }

    @Override
    public void fill(List<Category> cs) {
        for(Category c:cs) {
            fill(c);
        }
    }

    @Override
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for (Category c : cs) {
            List<Product> products =  c.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
        int reviewCount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);

        int saleCount = orderItemService.getNumber(p.getId());
        p.setSaleCount(saleCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        for (Product p:ps){
            setSaleAndReviewNumber(p);
        }
    }

    @Override
    public void setCategory(Product p) {
        Category c = categoryService.get(p.getCid());
        p.setCategory(c);
    }

    @Override
    public void setCategory(List<Product> ps) {
        for (Product p:ps){
            setCategory(p);
        }
    }

    @Override
    public List<Product> queryByKeyword(String keyword) {
        List<Product> ps = productMapper.queryByKeyword(keyword);
        productImageService.setFirstProductImage(ps);
        setCategory(ps);
        setSaleAndReviewNumber(ps);
        return ps;
    }

    @Override
    public List<Product> search(String keyword, int start, int size) {
        initDatabase2ES();
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
//                .add(QueryBuilders.matchPhraseQuery("name", keyword),
//                        ScoreFunctionBuilders.weightFactorFunction(100))
//                .scoreMode("sum")
//                .setMinScore(10);

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(
                QueryBuilders.matchPhraseQuery("name", keyword),
                ScoreFunctionBuilders.weightFactorFunction(100))
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                .setMinScore(10);


        Sort sort  = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = new PageRequest(start, size,sort);
        Pageable pageable = PageRequest.of(start,size,sort);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

        Page<Product> page = productESDAO.search(searchQuery);
        return page.getContent();

    }

    //初始化数据到es
    private void initDatabase2ES(){
//        Pageable pageable = new PageRequest(0, 5);
        Pageable pageable = PageRequest.of(0,5);//new PageRequest(0, 5)被弃用了
        Page<Product> page =productESDAO.findAll(pageable);
        if(page.getContent().isEmpty()) {
            List<Product> products = productMapper.queryAll();
            for (Product product : products) {
                productESDAO.save(product);
            }
        }
    }



}
