package study.small.comparator;

import study.small.entity.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {

    //价格比较器，把 价格低的放前面，升序
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice()-p2.getPromotePrice());
    }
}
