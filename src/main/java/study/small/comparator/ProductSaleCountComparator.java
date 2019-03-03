package study.small.comparator;

import study.small.entity.Product;

import java.util.Comparator;

public class ProductSaleCountComparator implements Comparator<Product> {

    //销量比较器，把 销量高的放前面，降序
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount()-p1.getSaleCount();
    }
}
