import java.util.Scanner;

public class hongongjava_05_2_6 {
    public static void main(String[] args) {
        boolean run = true;
        int studentNum = 0;
        int[] scores = null;
        Scanner scanner = new Scanner(System.in);


        while(run) {
            System.out.println("--------------------------");
            System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
            System.out.println("--------------------------");
            System.out.println("선택> ");

            int selectNo = Integer.parseInt(scanner.nextLine());

            if (selectNo == 1) {
                studentNum = Integer.parseInt(scanner.nextLine());
                scores = new int[studentNum];
            } else if (selectNo == 2) {
                for (int i=0; i<scores.length; i++) {
                    scores[i] = Integer.parseInt(scanner.nextLine());
                }
            } else if (selectNo == 3) {
                for (int i = 0; i <scores.length; i++) {
                    System.out.println(scores[i]);
                }
            } else if (selectNo == 4) {
                int sum = 0, count = 0, max = 0;
                double avg = 0;

                for (int i=0; i<scores.length; i++) {
                    sum += scores[i];
                    count++;
                    if (max < scores[i]) {
                        max = scores[i];
                    }
                }
                avg = (double) sum / count;

                System.out.println(max);
                System.out.println(avg);
            } else {

                break;
            }
        }
        System.out.println("프로그램 종료");
    }

}
