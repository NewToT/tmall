package com.wanczy.tmall.service;

import java.util.List;

import com.wanczy.tmall.pojo.Property;

public interface PropertyService {
	
public List<Property> list(int cid);

public void add(Property p);

public void delete(int id);

public Property get(int id);

public void update(Property p);
}
