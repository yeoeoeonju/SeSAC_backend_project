//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {

       int[][] array = {
               {95, 86},
               {83, 92, 96},
               {78, 83, 93, 87, 88}
       };

       int sum = 0;
       double avg = 0.0;
       int count = 0;

       for (int i=0; i<array.length; i++) {
           for (int k=0; k<array[i].length; k++) {
               sum += array[i][k];
               count++;
           }
       }
       avg = (double) sum / count;

       System.out.println("sum: " + sum);
        System.out.println("avg: " + avg);
    }
}