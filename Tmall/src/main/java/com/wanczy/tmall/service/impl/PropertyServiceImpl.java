package com.wanczy.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanczy.tmall.mapper.PropertyMapper;
import com.wanczy.tmall.pojo.Property;
import com.wanczy.tmall.service.PropertyService;
@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	PropertyMapper propertymapper;
	
	public List<Property> list(int cid) {
		return this.propertymapper.list(cid);
	}

	public void add(Property p) {
		this.propertymapper.add(p);

	}

	public void delete(int id) {
		this.propertymapper.delete(id);

	}

	public Property get(int id) {
		return this.propertymapper.get(id);
	}

	public void update(Property p) {
		this.propertymapper.update(p);
	}

}
