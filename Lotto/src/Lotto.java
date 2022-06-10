/*
 * �ζ� ü�� �ҽ�
 * 1�� ������ �ٷ� �ζ� �緯��
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
	// �ʿ��� ������
	static Random random;
	static ArrayList<Integer> draw, result;
	static ArrayList<ArrayList<Integer>> choice;
	static Scanner sc = new Scanner(System.in);
	static int tmp;
	
	// �޴� ���� 1: �ڵ�, 2: ����, 3: ����
	static int menu () {
		System.out.println("**** SKY LOTTO ****");
		System.out.println("1. �ڵ�\n2. ����\n3. ����");
		System.out.print(">> ");
		try {
			tmp = sc.nextInt();
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.\n");
			sc.nextLine();
			return 0;
		}
		
		if (tmp == 1 || tmp == 2 || tmp == 3) {
			return tmp;
		} else {
			return 0;
		}
	}
	
	// �ڵ� ����
	static void auto_lotto () {
		// 10�� �ݺ� (10����)
		for (int i = 0; i <= 9; i++) {
			System.out.print((i + 1) + "�� ���� ��ȣ : ");
			
			for (int j = 0; j <= 5; j++) {
				// ���� �� ����
				random = new Random();
				tmp = random.nextInt(45) + 1;
				if (chk_dup(choice.get(i), tmp)) {
					j--;
				} else {
					choice.get(i).add(tmp);
					System.out.print("[" + choice.get(i).get(j) + "] ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// ���� �ζ�
	static void manual_lotto () {
		// 10�� �ݺ� (10����)
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 5; j++) {
				System.out.print((i + 1) + "�� ���� " + (j + 1) + "�� : ");
				try {
					tmp = sc.nextInt();
					if (chk_num(tmp)) {
						System.out.println("1 ~ 45 ������ ���ڸ� �Է��� �ּ���.");
						j--;
					} else {
						if (chk_dup(choice.get(i), tmp)) {
							System.out.println("�ߺ��� �����Դϴ�.");
							j--;
						} else {
							choice.get(i).add(tmp);
						}
					}
				} catch (Exception e) {
					System.out.println("�߸��� �Է��Դϴ�.\n");
					sc.nextLine();
					j--;
				}
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("���� �Է� ��ȣ");
		for (int i = 0; i <= 4; i++) {
			System.out.print((i + 1) + "�� ���� ��ȣ : ");
			for (int j = 0; j <= 5; j++) {
				System.out.print("[" + choice.get(i).get(j) + "] ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
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
	
	// ��÷ Ȯ��
	static void get_result () {
		int score;
		for (ArrayList<Integer> ch: choice) {
			score = 0;
			for (int a: ch) {
				for (int b: draw) {
					// ���ʽ� ��ȣ ��
					if (b == draw.get(draw.size() - 1)) {
						// ���ʽ� ��ȣ ���� 5�� �¾��� ��� (2��)
						if (score == 5) {
							score = 7;
						}
					} else {
						if (a == b) {
							score++;
						}
					}
				}
			}
			result.add(score);
		}
		
		for (int i = 0; i <= choice.size() - 1; i++) {
			System.out.print((i + 1) + "�� ���� ��� : ");
			if (result.get(i) <= 2) {
				System.out.println("��");
			} else if (result.get(i) == 3) {
				System.out.println("5��");
			} else if (result.get(i) == 4) {
				System.out.println("�Ϳ� 4��");
			} else if (result.get(i) == 5) {
				System.out.println("�� 3��");
			} else if (result.get(i) == 6) {
				System.out.println("�ζ� �緯 ���� 1��");
			} else if (result.get(i) == 7) {
				System.out.println("��� 2��");
			}
		}
		
		System.out.println();
	}
	
	// ������
	Lotto () {
		// ���� ����
		int exit = 0;
		while (exit != 3) {
			// ���� �ʱ�ȭ
			draw = new ArrayList<Integer>();
			choice = new ArrayList<ArrayList<Integer>>();
			result = new ArrayList<Integer>();
			tmp = 0;
			
			// 2���� ����Ʈ �ʱ�ȭ
			for (int i = 0; i <= 9; i++) {
				choice.add(new ArrayList<Integer>());
			}
			
			// �޴� ���� �б�
			exit = menu();
			if (exit == 1) {
				// �ڵ� �ζ�
				auto_lotto();
			} else if (exit == 2) {
				// ���� �ζ�
				manual_lotto();
			} else if (exit == 3){
				// ����
				break;
			}
			
			if (exit == 1 || exit == 2) {
				// ��÷
				prize();
				
				// ���
				get_result();
			}
		}
	}
	
	public static void main (String[] args) {
		new Lotto();
	}
}
