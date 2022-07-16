package com.fun.design;


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

	//==================单例模式====================
	/**
	 * 单例模式：懒汉式
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
	 * 单例模式：饿汉式
	 * 静态内部类懒加载
	 */
	static class Singleton_04 {
		//静态内部类的作用，实现懒加载
		private static class SingletonHolder {
			private static Singleton_04 instance = new Singleton_04();
		}

		private Singleton_04() {
		}

		public static Singleton_04 getInstance() {
			return SingletonHolder.instance;
		}
	}

	//=====================建造者模式（传统）=======================
	//是将一个复杂的对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

	/**
	 * 目标对象
	 */
	public class Computer {
		private String cpu;//必须
		private String ram;//必须
		private int usbCount;//可选
		private String keyboard;//可选
		private String display;//可选

		public Computer(String cpu, String ram) {
			this.cpu = cpu;
			this.ram = ram;
		}

		public void setUsbCount(int usbCount) {this.usbCount = usbCount;}

		public void setKeyboard(String keyboard) {this.keyboard = keyboard;}

		public void setDisplay(String display) {this.display = display;}
	}

	/**
	 * 抽象构建者
	 */
	public abstract class ComputerBuilder {
		public abstract void setUsbCount();
		public abstract void setKeyboard();
		public abstract void setDisplay();
		public abstract Computer getComputer();
	}

	/**
	 * 实际构建者：苹果电脑
	 */
	public class MacComputerBuilder extends ComputerBuilder {
		private Computer computer;
		public MacComputerBuilder(String cpu, String ram) {
			computer = new Computer(cpu, ram);
		}
		@Override
		public void setUsbCount() {
			computer.setUsbCount(2);
		}
		@Override
		public void setKeyboard() {
			computer.setKeyboard("苹果键盘");
		}
		@Override
		public void setDisplay() {
			computer.setDisplay("苹果显示器");
		}
		@Override
		public Computer getComputer() {
			return computer;
		}
	}

	/**
	 * 实际构建者：联想电脑
	 */
	public class LenovoComputerBuilder extends ComputerBuilder {
		private Computer computer;
		public LenovoComputerBuilder(String cpu, String ram) {
			computer=new Computer(cpu,ram);
		}
		@Override
		public void setUsbCount() {
			computer.setUsbCount(4);
		}
		@Override
		public void setKeyboard() {
			computer.setKeyboard("联想键盘");
		}
		@Override
		public void setDisplay() {
			computer.setDisplay("联想显示器");
		}
		@Override
		public Computer getComputer() {
			return computer;
		}
	}

	/**
	 * 导演类
	 */
	public class ComputerDirector {
		public void makeComputer(ComputerBuilder builder){
			builder.setUsbCount();
			builder.setDisplay();
			builder.setKeyboard();
		}
	}
}
