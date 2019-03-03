package study.small.comparator;

import study.small.entity.Product;

import java.util.Comparator;

public class ProductAllComparator implements Comparator<Product> {

    //综合比较器:把 销量x评价作为条件， 高的放前面，降序
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount()*p2.getReviewCount()-p1.getSaleCount()*p1.getReviewCount();
    }
}
