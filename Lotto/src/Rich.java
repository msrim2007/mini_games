/*
 * 로또 체험 소스
 * 1등이 나오는지 확인을 위한 소스
 * 추첨 번호 하나에 여러 자동 번호를 비교
 * 1등 번호 5번 출력 후 종료
 */

import java.util.ArrayList;
import java.util.Random;

public class Rich {
	// 필요한 변수들
	static Random random;
	static ArrayList<Integer> draw;
	static ArrayList<Integer> choice;
	static int tmp, score, cnt = 0;
	
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
	
	// 생성자
	Rich () {
		// 추첨 번호 생성
		draw = new ArrayList<Integer>();
		prize();
		
		// 루프 조건
		int exit = 0;
		while (exit == 0) {
			if (score == 6) {
				// 재추첨
				draw = new ArrayList<Integer>();
				prize();
			}
			// 초기화
			choice = new ArrayList<Integer>();
			tmp = 0;
			score = 0;
			
			// 자동 번호 추출
			for (int i = 0; i <= 5; i++) {
				// 랜덤 숫자 추출
				random = new Random();
				tmp = random.nextInt(45) + 1;
				
				// 중복 확인
				if (chk_dup(choice, tmp)) {
					i--;
				} else {
					choice.add(tmp);
				}
			}
			
			// 결과 확인
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
				System.out.println("1등이다");
				System.out.print("번호 : ");
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
