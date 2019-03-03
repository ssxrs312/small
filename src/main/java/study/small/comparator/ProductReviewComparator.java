package study.small.comparator;

import study.small.entity.Product;

import java.util.Comparator;

public class ProductReviewComparator implements Comparator<Product> {

    //人气比较器,把 评价数量多的放前面,降序
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount()-p1.getReviewCount();
    }
}
