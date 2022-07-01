package com.fun.design;

import lombok.AllArgsConstructor;
import lombok.Data;

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

	//====================适配器模式：接口适配器=======================
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
	/**
	 * 桥接模式(Bridge Pattern)将抽象部分与它的实现部分分离，使它们都可以独立地变化。
	 */

	interface Implementor {
		void operationImpl();
	}
	public class ConcreteImplementorA implements Implementor {
		@Override
		public void operationImpl() {
			System.out.println("ConcreteImplementorA处理的业务逻辑...");
		}
	}
	@Data
	abstract class Abstration {

		private Implementor implementor;

		abstract void operation();
	}
	class RefinedAbstration extends Abstration {
		@Override
		void operation() {
			this.getImplementor().operationImpl();
			//do else...
		}
	}

	//====================装饰器模式=======================
	/**
	 * 装饰模式可以在不使用创造更多子类的情况下，将对象的功能加以扩展。
	 */
	interface Component {
		void operation();
	}
	class ConcreteComponent implements Component {

		@Override
		public void operation() {
			System.out.println("handle ...");
		}
	}

	@AllArgsConstructor
	class Decorator implements Component {
		private Component component;
		@Override
		public void operation() {
			component.operation();
		}
	}

	class ConcreteDecorator extends Decorator {
		public ConcreteDecorator(Component component) {
			super(component);
		}

		@Override
		public void operation() {
			super.operation();
			//do else...
		}
	}
}
