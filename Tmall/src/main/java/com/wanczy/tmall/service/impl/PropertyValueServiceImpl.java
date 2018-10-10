package com.wanczy.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanczy.tmall.mapper.PropertyValueMapper;
import com.wanczy.tmall.pojo.Product;
import com.wanczy.tmall.pojo.Property;
import com.wanczy.tmall.pojo.PropertyValue;
import com.wanczy.tmall.service.PropertyService;
import com.wanczy.tmall.service.PropertyValueService;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
	@Autowired
	PropertyValueMapper propertyValueMapper;
	@Autowired
	PropertyService propertyService;
	
	public void init(Product p) {
		List<Property> pts = propertyService.list(p.getCid());//这一类商品的属性都应该是一样的,根据产品获取分类
		
		for (Property pt : pts) {
			PropertyValue pv = this.get(pt.getId(), p.getId());
			if(null == pv) {
				pv = new PropertyValue();
				pv.setPid(p.getId());
				pv.setPtid(pt.getId());
				this.propertyValueMapper.insert(pv);
			}
		}

	}

	public void update(PropertyValue pv) {
		this.propertyValueMapper.update(pv);

	}

	public PropertyValue get(int ptid, int pid) {
		List<PropertyValue> pvs = this.propertyValueMapper.get(ptid, pid);//根据属性id和商品查询是否有属性值，没有就返回null
		if(pvs.isEmpty()) {
			return null;
		}
		return pvs.get(0);
	}

	public List<PropertyValue> list(int pid) {
		List<PropertyValue> result = this.propertyValueMapper.list(pid);
		
		for (PropertyValue pv: result) {
			Property property = this.propertyService.get(pv.getPtid());
			pv.setProperty(property);
		}
		return result;
	}

}
