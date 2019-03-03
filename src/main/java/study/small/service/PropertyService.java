package study.small.service;

import study.small.entity.Property;

import java.util.List;

public interface PropertyService {

    public void add(Property property);

    public void delete(int id);

    public void update(Property property);

    public Property get(int id);

    public List<Property> queryAll();

    public List<Property> queryByCid(int cid);
}
