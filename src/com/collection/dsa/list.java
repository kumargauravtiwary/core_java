package com.collection.dsa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class list {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<10;i++) {
			list.add(i);
		}
		for (Integer e :list) {
			System.out.println(e);
		}

	}

}
