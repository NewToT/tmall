package com.wanczy.tmall.comparator;

import java.util.Comparator;

import com.wanczy.tmall.pojo.Product;

public class ProductPriceComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		 float result = p1.getPromotePrice()-p2.getPromotePrice();
	        if(result>0)
	            {return (int) (result+1);}
	        else if(result==0)
	            {return 0;}
	        else
	        { return (int) (result-1);}
	    }

}
