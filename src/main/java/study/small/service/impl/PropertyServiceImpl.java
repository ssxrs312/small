package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.small.entity.Property;
import study.small.mapper.PropertyMapper;
import study.small.service.PropertyService;
import study.small.service.PropertyValueService;
import sun.security.provider.certpath.CertId;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    PropertyValueService propertyValueService;



    @Override
    public void add(Property property) {
        propertyMapper.add(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.delete(id);
    }

    @Override
    public void update(Property property) {
        propertyMapper.update(property);
    }

    @Override
    public Property get(int id) {
        Property p = propertyMapper.get(id);
        return p;
    }

    @Override
    public List<Property> queryAll() {
        List<Property> ps = propertyMapper.queryAll();
        return ps;
    }

    @Override
    public List<Property> queryByCid(int cid) {
        List<Property> ps = propertyMapper.queryByCid(cid);
        return ps;
    }
}
