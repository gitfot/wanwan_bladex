import org.checkerframework.checker.units.qual.C;

/**
 * @author wanwan 2022/7/20
 */
public class Test {

	class X{
		Y y=new Y();
		public X(){
			System.out.print("X");
		}
	}
	class Y{
		public Y(){
			System.out.print("Y");
		}
	}
	class Z extends X{
		Y y=new Y();
		public Z(){
			System.out.print("Z");
		}
	}

	public void build() {
		new Z();
	}

	public static void main(String[] args) {
		double d1=-0.5;
		System.out.println("Ceil d1="+Math.ceil(d1));
		System.out.println("floor d1="+Math.floor(d1));
	}

}



