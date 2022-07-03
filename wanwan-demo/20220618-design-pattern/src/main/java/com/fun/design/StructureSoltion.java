package com.fun.design;

import lombok.AllArgsConstructor;

import java.lang.reflect.Proxy;

/**
 * @author wanwan 2022/6/18
 */
public class StructureSoltion {

	//====================静态代理模式=======================
	/**
	 * 目标：在不改变原始类代码的情况下，通过引入代理类来给原始类增加功能
	 */


	interface Teach {
		void doTeach();
	}
	class TeachImpl implements Teach {
		@Override
		public void doTeach() {
			System.out.println("do teach...");
		}
	}

	@AllArgsConstructor
	class TeachProxy implements Teach {

		private Teach teach;
		@Override
		public void doTeach() {
			teach.doTeach();
			//增强方法
			System.out.println("handle else...");
		}
	}

	//====================动态代理模式=======================

	@AllArgsConstructor
	class DynamicProxy {
		private Object obj;
		public Object getInstance() {
			return Proxy.newProxyInstance(
				obj.getClass().getClassLoader(),
				obj.getClass().getInterfaces(),
				(proxy, method, args) -> {
					//增强方法
					System.out.println("handle else ...");
					Object invoke = method.invoke(obj, args);
					//do else...
					return invoke;
				});
		}
	}

	//====================适配器模式：类适配器=======================
	/**
	 * 原理：通过继承特性来实现适配器功能。
	 */
	class Phone {
		public void typecPhone() {
			System.out.println("typecPhone");
		}
	}
	interface Vga {
		void vagInterface();
	}
	class Typec2VGA extends Phone implements Vga {

		@Override
		public void vagInterface() {
			typecPhone();
			//do else...
			System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
		}
	}

	//====================适配器模式：对象适配器=======================

	/**
	 * 原理：通过组合方式来实现适配器功能。
	 */
	@AllArgsConstructor
	class Typec2VGA2 implements Vga {

		private Phone phone;

		@Override
		public void vagInterface() {
			phone.typecPhone();
			//do else...
			System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
		}
	}

	//====================适配器模式：接口适配器=======================
	/**
	 * 原理：借助抽象类来实现适配器功能。
	 * 场景：当不需要全部实现接口提供的方法时，可先设计一个抽象类实现接口提供一个默认实现
	 */
	interface Protocols {
		void typec();
		void typec2Hdmi();
		void typec2VGA();
	}

	abstract class Adaptor implements Protocols {
		@Override
		public void typec() {}
		@Override
		public void typec2Hdmi() {}
		@Override
		public void typec2VGA() {}
	}

	class VGAAdaptor extends Adaptor {
		@Override
		public void typec2VGA() {
			//do else...
			System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
		}
	}


	//====================桥接模式=======================
	//桥接模式(Bridge Pattern)将抽象部分与它的实现部分分离，使它们都可以独立地变化。

	/**
	 * 品牌维度
	 */
	public interface Brand {
		void info();
	}

	class LenovoBrand implements Brand {

		@Override
		public void info() {
			System.out.println("联想");
		}
	}

	class AsusBrand implements Brand {
		@Override
		public void info() {
			System.out.println("华硕");
		}
	}

	/**
	 * 品牌和类型的“桥”
	 */
	public abstract class Computer {
		protected Brand brand;
		public Computer(Brand brand) {
			this.brand = brand;
		}

		public void info() {
			this.brand.info();
		}
	}

	/**
	 * 类型维度
	 */
	class Desktop extends Computer {
		public Desktop(Brand brand) {
			super(brand);
		}

		@Override
		public void info() {
			super.info();
			System.out.println("台式电脑");
		}
	}

	class Laptop extends Computer {
		public Laptop(Brand brand) {
			super(brand);
		}

		@Override
		public void info() {
			super.info();
			System.out.println("笔记本电脑");
		}
	}



	//====================装饰器模式=======================
	//装饰模式是在不必改变原类和使用继承的情况下，动态地扩展一个对象的功能。
	// 它是通过创建一个包装对象，也就是装饰来包裹真实的对象

	/**
	 * 原始对象接口
	 */
	public interface ICoffee {
		void makeCoffee();
	}

	public class OriginalCoffee implements ICoffee {
		@Override
		public void makeCoffee() {
			System.out.print("原味咖啡 ");
		}
	}

	/**
	 * 装饰器类，用于包装基础对象
	 */
	@AllArgsConstructor
	public abstract class CoffeeDecorator implements ICoffee {
		private final ICoffee coffee;

		@Override
		public void makeCoffee() {
			coffee.makeCoffee();
		}
	}

	/**
	 * 实际装饰着
	 */
	public class MilkDecorator extends CoffeeDecorator {
		public MilkDecorator(ICoffee coffee) {
			super(coffee);
		}
		@Override
		public void makeCoffee() {
			super.makeCoffee();
			addMilk();
		}
		private void addMilk(){
			System.out.print("加奶 ");
		}
	}
	public class SugarDecorator extends CoffeeDecorator {
		public SugarDecorator(ICoffee coffee) {
			super(coffee);
		}
		@Override
		public void makeCoffee() {
			super.makeCoffee();
			addSugar();
		}
		private void addSugar(){
			System.out.print("加糖");
		}
	}




	//====================外观模式=======================
	//定义：提供一个高层次的接口，使得子系统更易于使用

	public interface Shape {
		void draw();
	}

	public class Rectangle implements Shape {

		@Override
		public void draw() {
			System.out.println("Rectangle::draw()");
		}
	}

	public class Square implements Shape {

		@Override
		public void draw() {
			System.out.println("Square::draw()");
		}
	}

	public class Circle implements Shape {

		@Override
		public void draw() {
			System.out.println("Circle::draw()");
		}
	}

	/**
	 * 外观类
	 */
	@AllArgsConstructor
	public class ShapeMaker {
		private final Shape circle;
		private final Shape rectangle;
		private final Shape square;

		public void drawCircle(){
			circle.draw();
		}
		public void drawRectangle(){
			rectangle.draw();
		}
		public void drawSquare(){
			square.draw();
		}
	}

}
