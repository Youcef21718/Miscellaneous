public class chain
{
    public static void main() {
        int[] dims = new int[]{5,4,6,2,7};
        matrixChainOrder(dims);
        
        int[] count = new int[1];
        
        int n = dims.length-1; 
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                s[i][j] = -1;
            }
        }
        int cost = cost(0, n-1, dims, m, s, count);
        print(m);
        print(s);
        
        System.out.println(count[0]);
    }
    
    public static int cost(int i, int j, int dims[], int[][] m, int[][] s, int[] count) {
        System.out.println(i+":"+j);
        count[0]++;
        
        if(i==j) {
            return 0;
        }
        
        if(s[i][j] != -1) {
            return m[i][j];
        }
        
        else {
            m[i][j] = Integer.MAX_VALUE;
            for(int k=i; k<=j-1; k++) {
                int cost = cost(i, k, dims, m, s, count) + cost(k+1, j, dims, m, s, count) + dims[i]*dims[k+1]*dims[j+1];
                if (cost < m[i][j]) {
                    m[i][j] = cost;
                    s[i][j] = k;
                }
            }
            return m[i][j];
        }
    }
    
    public static void matrixChainOrder(int dims[]) {
        int n = dims.length-1; 
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                s[i][j] = -1;
            }
        }
        
        int count = 0;
        //subsequence lengths
        for(int len=2; len<=n; len++) {
            //starting point of subsequence
            for(int i=0; i<=n-len; i++) {
                count++;
                
                //end point of subsequence
                int j = i+(len-1);
                m[i][j] = Integer.MAX_VALUE;
                
                //possible splits                
                for(int k=i; k<=j-1; k++) {
                    int cost = m[i][k] + m[k+1][j] + dims[i]*dims[k+1]*dims[j+1];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        s[i][j] = k;
                    }
                }
            }
        }
        print(m);
        print(s);
        System.out.println(count);
    }
    
    public static void print(int[][] array) {
        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
