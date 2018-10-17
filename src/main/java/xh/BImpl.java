package xh;

public class BImpl extends AImpl implements B {

	public void sysoB() {
		System.out.println("BBBBImpl");
	}

	public static void main(String[] args) {
		BImpl bImpl = new BImpl();
		bImpl.sysoB();
	}

}
