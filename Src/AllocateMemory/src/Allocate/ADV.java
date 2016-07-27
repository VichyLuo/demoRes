package Allocate;

public class ADV {
	
	
	/**
	 * catch 捕获异常， throws、throw抛出异常 （对于第一次定义异常，必须使用throws 和throw组合），然后在使用时可用try抛出异常，catch
	 * 来捕获（可以处理，也可以抛出）
	 * throw必须和try catch使用或throws来使用
	 * try 发现异常后，try后面的代码将不再运行
	 * @param a
	 * @throws TestException
	 */
	public void test(int a) throws TestException {
		if (a > 2) {
			a += 2;
			System.out.println("good");
		} else {
			throw new TestException();
		}
	}
	
	
	
	public static void main(String [] args)
	{
		ADV adv = new ADV();
		int a = 1;
		try {
			adv.test(a);
		} catch (TestException e) {
			e.printStackTrace();
		}
	}
}
