package com.fun.design;

/**
 * 建造者模式（简化）
 * 场景：当一个类的构造函数参数个数超过4个，而且这些参数有些是可选的参数，考虑使用构造者模式
 */
public class Computer {
	private String cpu;//必须
	private String ram;//必须
	private int usbCount;//可选
	private String keyboard;//可选
	private String display;//可选

	private Computer(ComputerBuilder builder){
		this.cpu=builder.cpu;
		this.ram=builder.ram;
		this.usbCount=builder.usbCount;
		this.keyboard=builder.keyboard;
		this.display=builder.display;
	}

	/**
	 * 建造者
	 */
	public static class ComputerBuilder {
		private final String cpu;//必须
		private final String ram;//必须
		private int usbCount;//可选
		private String keyboard;//可选
		private String display;//可选

		public ComputerBuilder(String cup, String ram) {
			this.cpu = cup;
			this.ram = ram;
		}

		public Computer build(){
			return new Computer(this);
		}

		public void setUsbCount(int usbCount) {this.usbCount = usbCount;}
		public void setKeyboard(String keyboard) {this.keyboard = keyboard;}
		public void setDisplay(String display) {this.display = display;}
	}
}

class Client {

	public Computer computerBuildTest() {
		Computer.ComputerBuilder builder=new Computer.ComputerBuilder("因特尔","三星");
		builder.setDisplay("三星24寸");
		builder.setKeyboard("罗技");
		builder.setUsbCount(2);
		return builder.build();
	}
}
