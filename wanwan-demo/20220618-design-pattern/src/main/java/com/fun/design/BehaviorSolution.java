package com.fun.design;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanwan 2022/6/18
 */
public class BehaviorSolution {

	//====================策略模式=======================
	/**
	 * 策略模式(Strategy Pattern)定义一系列算法，将每一个算法封装起来，并让它们可以相互替换。
	 */
	abstract class AbstractStrategy {
		abstract void algorithms();
	}
	class ConcreteStrategyA extends AbstractStrategy {

		@Override
		void algorithms() {
			System.out.println("algorithms A ....");
		}
	}
	class ConcreteStrategyB extends AbstractStrategy {

		@Override
		void algorithms() {
			System.out.println("algorithms B ....");
		}
	}

	@AllArgsConstructor
	class Context {
		private AbstractStrategy abstractStrategy;

		void algorithms() {
			abstractStrategy.algorithms();
		}
	}

	static class StrategyClient {
		public static void main(String[] args) {
			AbstractStrategy strategyA = new BehaviorSolution().new ConcreteStrategyA();
			AbstractStrategy strategyB = new BehaviorSolution().new ConcreteStrategyB();
			Context context = new BehaviorSolution().new Context(strategyA);
			context.algorithms();
		}
	}

	//====================模板方法模式=======================
	/**
	 * 定义：模板模式(Template Pattern)定义一个操作中算法的框架，而将一些步骤延迟到子类中。
	 * 		模板方法模式使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤
	 * 目的：为了提高代码的复用性和系统的灵活性
	 */
	abstract class AbstractCarFactory {
		void createFrame() {
			System.out.println("制造框架");
		}
		void installComponent() {
			System.out.println("组装");
		}
		abstract void color();//选择颜色
		void testing() {
			System.out.println("落地测试");
		}
		void build() {
			createFrame();
			installComponent();
			color();
			testing();
		}
	}

	class WhiteCarFactory extends AbstractCarFactory {

		@Override
		void color() {
			System.out.println("喷涂白色车漆");
		}
	}

	class RedCarFactory extends AbstractCarFactory {

		@Override
		void color() {
			System.out.println("喷涂红色车漆");
		}
	}

	//====================观察者模式=======================
	/**
	 * 定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新
	 */
	abstract class Subject {

		List<Observer> observers = new ArrayList<>();
		void add(Observer obj) {
			observers.add(obj);
		}
		void remove(Observer obj) {
			observers.remove(obj);
		}
		public void notifyObserver() {
			for (Observer observer : observers) {
				observer.receive();
			}
		}
	}

	class ConcreteSubject extends Subject {
		void publish() {
			System.out.println("都给我听着！");
			super.notifyObserver();
		}
	}

	interface Observer {
		/**
		 * 通知接收方法
		 */
		void receive();
	}

}
