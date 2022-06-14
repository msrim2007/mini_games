/*
 * �ζ� ü�� �ҽ�
 * 1���� �������� Ȯ���� ���� �ҽ�
 * ��÷ ��ȣ �ϳ��� ���� �ڵ� ��ȣ�� ��
 * 1�� ��ȣ 5�� ��� �� ����
 */

import java.util.ArrayList;
import java.util.Random;

public class Rich {
	// �ʿ��� ������
	static Random random;
	static ArrayList<Integer> draw;
	static ArrayList<Integer> choice;
	static int tmp, score, cnt = 0;
	
	// ��÷
	static void prize () {
		System.out.print("�ζ� ��÷ ��ȣ : ");
		for (int i = 0; i <= 6; i++) {
			// ���� ���� ����
			random = new Random();
			tmp = random.nextInt(45) + 1;
			
			if (chk_dup(draw, tmp)) {
				i--;
			} else {
				draw.add(tmp);
				System.out.print("[" + draw.get(i) + "] ");
			}
		}
		System.out.println();
	}
	
	// �ߺ� Ȯ��
	static boolean chk_dup (ArrayList<Integer> list, int num) {
		if (list.size() == 0) {
			return false;
		} else {
			for (int list_num: list) {
				if (list_num == num) {
					return true;
				}
			}
			return false;
		}
	}
	
	// ���� ���� Ȯ��
	static boolean chk_num (int num) {
		if (num <= 0 || num >= 46) {
			return true;
		} else {
			return false;
		}
	}
	
	// ������
	Rich () {
		// ��÷ ��ȣ ����
		draw = new ArrayList<Integer>();
		prize();
		
		// ���� ����
		int exit = 0;
		while (exit == 0) {
			if (score == 6) {
				// ����÷
				draw = new ArrayList<Integer>();
				prize();
			}
			// �ʱ�ȭ
			choice = new ArrayList<Integer>();
			tmp = 0;
			score = 0;
			
			// �ڵ� ��ȣ ����
			for (int i = 0; i <= 5; i++) {
				// ���� ���� ����
				random = new Random();
				tmp = random.nextInt(45) + 1;
				
				// �ߺ� Ȯ��
				if (chk_dup(choice, tmp)) {
					i--;
				} else {
					choice.add(tmp);
				}
			}
			
			// ��� Ȯ��
			for (int a: draw) {
				for (int b: choice) {
					if (a == draw.get(draw.size() - 1)) {
						if (score == 5 && a == b) {
							score = 7;
							break;
						}
					} else {
						if (a == b) {
							score++;
						}
					}
				}
			}
			
			if (score == 6) {
				cnt++;
				System.out.println("1���̴�");
				System.out.print("��ȣ : ");
				for (int i: choice) {
					System.out.print("[" + i + "] ");
				}
				System.out.println();
			}
			
			if (cnt == 5) {
				exit = 1;
			}
		}
	}
	
	public static void main (String[] args) {
		new Rich();
	}
}
