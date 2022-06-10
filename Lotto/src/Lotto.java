/*
 * 로또 체험 소스
 * 1등 나오면 바로 로또 사러감
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
	// 필요한 변수들
	static Random random;
	static ArrayList<Integer> draw, result;
	static ArrayList<ArrayList<Integer>> choice;
	static Scanner sc = new Scanner(System.in);
	static int tmp;
	
	// 메뉴 선택 1: 자동, 2: 수동, 3: 종료
	static int menu () {
		System.out.println("**** SKY LOTTO ****");
		System.out.println("1. 자동\n2. 수동\n3. 종료");
		System.out.print(">> ");
		try {
			tmp = sc.nextInt();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.\n");
			sc.nextLine();
			return 0;
		}
		
		if (tmp == 1 || tmp == 2 || tmp == 3) {
			return tmp;
		} else {
			return 0;
		}
	}
	
	// 자동 추출
	static void auto_lotto () {
		// 10번 반복 (10게임)
		for (int i = 0; i <= 9; i++) {
			System.out.print((i + 1) + "번 게임 번호 : ");
			
			for (int j = 0; j <= 5; j++) {
				// 랜덤 값 추출
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
	
	// 수동 로또
	static void manual_lotto () {
		// 10번 반복 (10게임)
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 5; j++) {
				System.out.print((i + 1) + "번 게임 " + (j + 1) + "번 : ");
				try {
					tmp = sc.nextInt();
					if (chk_num(tmp)) {
						System.out.println("1 ~ 45 까지의 숫자만 입력해 주세요.");
						j--;
					} else {
						if (chk_dup(choice.get(i), tmp)) {
							System.out.println("중복된 숫자입니다.");
							j--;
						} else {
							choice.get(i).add(tmp);
						}
					}
				} catch (Exception e) {
					System.out.println("잘못된 입력입니다.\n");
					sc.nextLine();
					j--;
				}
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("수동 입력 번호");
		for (int i = 0; i <= 4; i++) {
			System.out.print((i + 1) + "번 게임 번호 : ");
			for (int j = 0; j <= 5; j++) {
				System.out.print("[" + choice.get(i).get(j) + "] ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// 추첨
	static void prize () {
		System.out.print("로또 추첨 번호 : ");
		for (int i = 0; i <= 6; i++) {
			// 랜덤 정수 추출
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
	
	// 중복 확인
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
	
	// 숫자 범위 확인
	static boolean chk_num (int num) {
		if (num <= 0 || num >= 46) {
			return true;
		} else {
			return false;
		}
	}
	
	// 당첨 확인
	static void get_result () {
		int score;
		for (ArrayList<Integer> ch: choice) {
			score = 0;
			for (int a: ch) {
				for (int b: draw) {
					// 보너스 번호 비교
					if (b == draw.get(draw.size() - 1)) {
						// 보너스 번호 제외 5개 맞았을 경우 (2등)
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
			System.out.print((i + 1) + "번 게임 결과 : ");
			if (result.get(i) <= 2) {
				System.out.println("꽝");
			} else if (result.get(i) == 3) {
				System.out.println("5등");
			} else if (result.get(i) == 4) {
				System.out.println("와우 4등");
			} else if (result.get(i) == 5) {
				System.out.println("헐 3등");
			} else if (result.get(i) == 6) {
				System.out.println("로또 사러 가자 1등");
			} else if (result.get(i) == 7) {
				System.out.println("대박 2등");
			}
		}
		
		System.out.println();
	}
	
	// 생성자
	Lotto () {
		// 루프 조건
		int exit = 0;
		while (exit != 3) {
			// 변수 초기화
			draw = new ArrayList<Integer>();
			choice = new ArrayList<ArrayList<Integer>>();
			result = new ArrayList<Integer>();
			tmp = 0;
			
			// 2차원 리스트 초기화
			for (int i = 0; i <= 9; i++) {
				choice.add(new ArrayList<Integer>());
			}
			
			// 메뉴 선택 분기
			exit = menu();
			if (exit == 1) {
				// 자동 로또
				auto_lotto();
			} else if (exit == 2) {
				// 수동 로또
				manual_lotto();
			} else if (exit == 3){
				// 종료
				break;
			}
			
			if (exit == 1 || exit == 2) {
				// 추첨
				prize();
				
				// 결과
				get_result();
			}
		}
	}
	
	public static void main (String[] args) {
		new Lotto();
	}
}
