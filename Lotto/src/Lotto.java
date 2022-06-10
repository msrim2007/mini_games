import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * 로또 체험 소스
 * 1등 나오면 바로 로또 사러감
 */
public class Lotto {
	// 필요한 변수들
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
