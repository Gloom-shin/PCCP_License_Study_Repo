package JAN.day23;

public class Q1debug {
    public static void main(String[] args) {
        int[][] maze1 = {{1, 4}, {0, 0}, {2, 3}};
        int[][] maze2 = {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}};

        int[][] maze3 = {{4, 3, 0, 0}, {5, 5, 5, 0}, {1, 0, 0, 0}, {2, 0, 0, 0}};
        Q1 q1 = new Q1();


//        int solution = q1.solution(maze1);
//        System.out.println("solution = " + solution);
        int solution2 = q1.solution(maze2);
        System.out.println("solution2 = " + solution2);

        int solution3 = q1.solution(maze3);
        System.out.println("solution3 = " + solution3);

    }
}
