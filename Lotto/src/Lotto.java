import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * �ζ� ü�� �ҽ�
 * 1�� ������ �ٷ� �ζ� �緯��
 */
public class Lotto {
	// �ʿ��� ������
	Random random;
	ArrayList<?> draw, choice, result;	
	Scanner sc = new Scanner(System.in);
	int tmp;
	
	
	
	

	Lotto() {
		draw = new ArrayList<Integer>();
		choice = new ArrayList<ArrayList<Integer>>();
		result = new ArrayList<Integer>();
		
		
	}
	
	public static void main(String[] args) {
		new Lotto();
	}
}
