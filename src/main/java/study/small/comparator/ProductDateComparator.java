package study.small.comparator;

import study.small.entity.Product;

import java.util.Comparator;

public class ProductDateComparator implements Comparator<Product> {

    //新品比较器：把 创建日期晚的放前面,升序
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getCreateDate().compareTo(p2.getCreateDate());
    }
}
