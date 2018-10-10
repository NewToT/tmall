package com.wanczy.tmall.comparator;

import java.util.Comparator;

import com.wanczy.tmall.pojo.Product;

public class ProductReviewComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		// TODO Auto-generated method stub
		return p2.getReviewCount() - p1.getReviewCount();
	}

}
