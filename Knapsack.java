/** Knapsack.java
 * 
 * Defines the implementation of a recursive method and dynamic
 * programming method to solve the 0-1 Knapsack problem
 * 
 * Jordan Ho and Paul Patrick Bellosillo
 * March 19, 2020
 * 
 */
public class Knapsack{
    public static void main(String[] args){
        int w = 5;
        int len = 4;
        int[] weights = new int[len];
        int[] vals = new int[len];

        // Inputting item 0
        weights[0] = 1;
        vals[0] = 6;
        
        // Inputting item 1
        weights[1] = 2;
        vals[1] = 10;

        // Inputting item 3
        weights[2] = 3;
        vals[2] = 12;

        // Inputting item 4
        weights[3] = 1;
        vals[3] = 7;

        weights[4] = 2;
        vals[4] = 7;

        weights[5] = 4;
        vals[5] = 9;

        weights[6] = 2;
        vals[6] = 11;

        weights[7] = 6;
        vals[7] = 15;

        weights[8] = 9;
        vals[8] = 20;

        weights[9] = 1;
        vals[9] = 5;

        weights[10] = 1;
        vals[10] = 7;

        weights[3] = 1;
        vals[3] = 7;

        weights[3] = 1;
        vals[3] = 7;

        int maxValue = knapsack(w, weights, vals, len);
        System.out.println("Total Recursive = " + maxValue);
        System.out.println("Total dp: " + knapsack_dp(w, weights, vals, len));
    }

    public static int knapsack_r(int w, int[] weights, int vals[], int len, int index) {
        
        // Checks if the index exceeds max length
        if (index >= len) {
            return 0;
        }

        // Checks if there is more space in the knapsack
        if (w - weights[index] < 0) {
            return knapsack_r(w, weights, vals, len, index + 1);
        }

        int valCheck = knapsack_r(w, weights, vals, len, index + 1);

        System.out.println("Valcheck = " + valCheck);

        // returns the max value of the two
        if (valCheck > (knapsack_r(w - weights[index], weights, vals, len, index + 1) + vals[index])) {
            return valCheck;
        }

        return knapsack_r(w - weights[index], weights, vals, len, index + 1) + vals[index];
    }

    public static int knapsack(int w, int[] weights, int[] vals, int len) {
        // Checks for if max weight and length of arrays are at least 0
        if (w < 0 || len < 0){
            return 0;
        }
        // Check if weights and vals arrays are the same length
        else if (weights.length != len || vals.length != len){
            return 0;
        }

        System.out.println("Passed weights and length checks");

        // checks if all weights and values are non-negative
        for (int i = 0; i < len; i++){
            System.out.println(i + " - weight: " + weights[i] + " & value: " + vals[i]);
            if (weights[i] < 0 || vals[i] < 0){
                return 0;
            }
        }

        System.out.println("Passed non-negative checks");

        return knapsack_r(w, weights, vals, len, 0);
    }

    public static int knapsack_dp(int w, int[] weights, int[] vals, int len){
        int[][] T = new int[len + 1][w + 1];

        // Initializes first row and column to 0
        for (int i = 0; i < len + 1; i++){
            T[i][0] = 0;
        } 
        for (int i = 0; i < w + 1; i++){
            T[0][i] = 0;
        }

        for (int i = 1; i <= len; i++){
            for (int j = 1; j <= w; j++){

                // Checks if the weight is bigger than current max weight
                if (weights[i-1] > j){
                    T[i][j] = T[i-1][j];
                }
                else{
                    // Saves the bigger value to the table
                    if (T[i-1][j] > T[i-1][j-weights[i-1]] + vals[i-1]){
                        T[i][j] = T[i-1][j];
                    }
                    else {
                        T[i][j] = T[i-1][j-weights[i-1]] + vals[i-1];
                    }
                }
            }
        }

        // prints table
        for (int i = 1; i <= len; i++){
            for (int j = 1; j <= w; j++){
                System.out.print(T[i][j] + " ");
            }
            System.out.print("\n");
        }

        return T[len][w];
    }
}