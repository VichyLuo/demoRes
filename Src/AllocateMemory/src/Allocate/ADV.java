package Allocate;

public class ADV {
	
	
	/**
	 * catch �����쳣�� throws��throw�׳��쳣 �����ڵ�һ�ζ����쳣������ʹ��throws ��throw��ϣ���Ȼ����ʹ��ʱ����try�׳��쳣��catch
	 * �����񣨿��Դ���Ҳ�����׳���
	 * throw�����try catchʹ�û�throws��ʹ��
	 * try �����쳣��try����Ĵ��뽫��������
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
