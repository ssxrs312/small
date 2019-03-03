package study.small.service;

import study.small.entity.Product;
import study.small.entity.Property;
import study.small.entity.PropertyValue;

import java.util.List;

public interface PropertyValueService {

    public void add(PropertyValue propertyValue);

    public void delete(int id);

    public void update(PropertyValue propertyValue);

    public PropertyValue get(int id);

    public List<PropertyValue> queryAll();

    public void init(Product product);

    public PropertyValue queryByProperty(int ptid, int pid);

    public List<PropertyValue> queryByPid(int pid);


}
