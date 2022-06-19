package com.fun.design;

import lombok.Data;

/**
 * @author wanwan 2022/6/18
 */
public class CreateSolution {

	//==================静态工厂方法====================

	interface Shoes {
		void exercise();
	}

	class LiningShoes implements Shoes {
		@Override
		public void exercise() {
			System.out.println("LiningShoes start...");
		}
	}

	class NikeShoes implements Shoes {
		@Override
		public void exercise() {
			System.out.println("NikeShoes start...");
		}
	}

	interface Factory {
		Shoes liningShoes();
		Shoes nikeShoes();
	}
	class ShoeFactory implements Factory {
		@Override
		public Shoes liningShoes() {
			return new LiningShoes();
		}

		@Override
		public Shoes nikeShoes() {
			return new NikeShoes();
		}
	}

	/**
	 * 单例模式
	 * 双重校验锁 + volatile防止重排
	 */
	static class SingleTon {

		private static volatile SingleTon instance;

		public SingleTon getInstance() {
			if (instance == null) {
				synchronized (SingleTon.class) {
					if (instance == null) {
						/*
						 * 这一步可能会发生重排序 （分配内存 -> 初始化 -> 指向分配的内存）
						 * 发生重排：1 3 2 ，就可能导致另一个线程获取到空对象
						 */
						return new SingleTon();
					}
				}
			}
			return instance;
		}
	}

	/**
	 * 单例模式
	 * 静态内部类懒加载
	 */
	static class Singleton_04 {
		private static class SingletonHolder {
			private static Singleton_04 instance = new Singleton_04();
		}

		private Singleton_04() {
		}

		public static Singleton_04 getInstance() {
			return SingletonHolder.instance;
		}
	}

	//=====================建造者模式=======================

	@Data
	class Product {
		private String name;
		private String type;

		public void showProduct() {
			System.out.println("name:" + name + "\n"+ "type:" + type);
		}
	}

	interface Builder {
		void setPart(String arg1, String arg2);

		Product getProduct();
	}

	class ConcreteBuilder implements Builder {
		private Product product = new Product();

		@Override
		public void setPart(String arg1, String arg2) {
			product.setName(arg1);
			product.setType(arg2);
		}

		@Override
		public Product getProduct() {
			return product;
		}
	}

	class Director {
		Builder builder = new ConcreteBuilder();
		public Product getProduct() {
			builder.setPart("123", "test");
			return builder.getProduct();
		}
	}



}
