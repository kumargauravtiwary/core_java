package DynamicProgramming;
//Solve the Fibonacci sequence using memoization and tabulation, and compare withthe naive recursive approach.
public class fibonacci {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 10;
        System.out.println("Fibonacci of " + n + " using naive recursion: " + fibonacciNaive(n));
        System.out.println("Fibonacci of " + n + " using memoization: " + fibonacciMemoization(n));
        System.out.println("Fibonacci of " + n + " using tabulation: " + fibonacciTabulation(n));
    }
    
    //Naive recursive approach
    public static int fibonacciNaive(int n) {
        if (n <= 1) return n;
        return fibonacciNaive(n - 1) + fibonacciNaive(n - 2);
    }
    
    //Memoization approach
    public static int fibonacciMemoization(int n) {
        int[] memo = new int[n + 1];
        return fibonacciMemoizationHelper(n, memo);
    }
    
    private static int fibonacciMemoizationHelper(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fibonacciMemoizationHelper(n - 1, memo) + fibonacciMemoizationHelper(n - 2, memo);
        return memo[n];
    }
    
    //Tabulation approach
    public static int fibonacciTabulation(int n) {
        if (n <= 1) return n;
        int[] table = new int[n + 1];
        table[0] = 0;
        table[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
        
        return table[n];
    }
}
