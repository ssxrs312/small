package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.small.entity.Product;
import study.small.entity.Property;
import study.small.entity.PropertyValue;
import study.small.mapper.PropertyValueMapper;
import study.small.service.PropertyService;
import study.small.service.PropertyValueService;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    @Override
    public void add(PropertyValue propertyValue) {
        propertyValueMapper.add(propertyValue);
    }

    @Override
    public void delete(int id) {
        propertyValueMapper.delete(id);
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.update(propertyValue);
    }

    @Override
    public PropertyValue get(int id) {
        PropertyValue pv = propertyValueMapper.get(id);
        return pv;
    }

    @Override
    public List<PropertyValue> queryAll() {
        List<PropertyValue> pvs = propertyValueMapper.queryAll();
        return pvs;
    }

    @Override
    public void init(Product product) {
        List<Property> properties = propertyService.queryByCid(product.getCid());
        for (Property p:properties){
            PropertyValue pv = queryByProperty(p.getId(), product.getId());
//            pv.setProperty(p);

            if (null==pv){
                pv = new PropertyValue();
                pv.setPtid(p.getId());
                pv.setPid(product.getId());
                add(pv);
            }
        }
    }

    @Override
    public PropertyValue queryByProperty(int ptid, int pid) {
        PropertyValue propertyValue = new PropertyValue();
        propertyValue.setPtid(ptid);
        propertyValue.setPid(pid);
        PropertyValue pv = propertyValueMapper.queryByProperty(propertyValue);
        return pv;
    }

    @Override
    public List<PropertyValue> queryByPid(int pid) {
        List<PropertyValue> pvs = propertyValueMapper.queryByPid(pid);
        for (PropertyValue pv:pvs){
            pv.setProperty(propertyService.get(pv.getPtid()));
        }
        return pvs;
    }

}
