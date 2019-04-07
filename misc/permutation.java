import java.util.Random;

//http://www.i-programmer.info/programming/theory/2744-how-not-to-shuffle-the-kunth-fisher-yates-algorithm.html
public class permutation
{
    public static void main() {        
        //printArray(perm(5));
        //printArray(perm(10,5));
        //permlist(3);
        
        permlist(3,2);
    }
    
    public static boolean checkPerm(int[] perm) {
       int[] check = new int[perm.length];        
       for(int i=0; i<perm.length; i++) {
           if(!(0<=perm[i] && perm[i]<perm.length)) {
               return false;
            }
            else {
                if(check[perm[i]] == 1) {
                    return false;
                }
            
                else {
                    check[perm[i]] = 1;
                }
            }
       }
       return true;
    }
    
    public static int[] inv(int[] perm) {
        assert checkPerm(perm) : "not a permutation";
        
        int[] inv = new int[perm.length];
        for(int i=0; i<perm.length; i++) {
            inv[perm[i]] = i;
        }
        return inv;
    }
    
    public static void permlist(int a, int b) {
        assert (a>=b) : "invalid arguments";
        assert (a>=0 && b>=0) : "negative length error";
        
        int[] index = new int[a];
        for(int i=0; i<a; i++) {
            index[i] = i;
        }
        permlist(index, 0, b);
    }
    
    public static void permlist(int[] index, int i, int b)
    {
        if(i == b) {
            printArray(prefix(index, b));
        }
        
        else {
            for(int j=i; j<index.length; j++) {
                swap(index, i, j);
                permlist(index, i+1, b);
                swap(index, j, i);
            }
        }
    }
    
    public static void permlist(int a) {
        assert (a>= 0) : "negative length error";
        
        int[] index = new int[a];
        for(int i=0; i<a; i++) {
            index[i] = i;
        }
        permlist(index, 0);
    }
    
    public static void permlist(int[] index, int i)
    {
        //could also terminate at i == index.length
        //doing it this way saves a computation step
        if(i == index.length-1) {
            printArray(index);
        }
        
        else {
            for(int j=i; j<index.length; j++) {
                swap(index, i, j);
                permlist(index, i+1);
                swap(index, j, i);
            }
        }
    }
    
    public static void swap(int[] index, int i, int j) {
        int value1 = index[i];
        int value2 = index[j];
        
        index[j] = value1;
        index[i] = value2;
    }
    
    public static int[] perm(int a)
    {
        assert (a>= 0) : "negative length error";
        
        Random r = new Random();
        int[] index = new int[a];
        
        index[0] = a; 
        int track = 0;
        
        for(int j=0; j<a; j++)
        {
            int swap = r.nextInt(a-j)+j;
            
            int value1 = (index[swap] == 0) ? swap : index[swap];
            int value2 = (index[j] == 0) ? j : index[j];
            
            if(index[j] == a) {
                track = swap;
            }
            
            if(index[swap] == a) {
                track = j;
            }
            
            index[j] = value1;
            index[swap] = value2;
        }
        
        index[track] = 0;
        
        return index;
    }
    
    public static int[] perm(int a, int b)
    {
        assert (a>=b) : "invalid arguments";
        assert (a>=0 && b>=0) : "negative length error";
        
        Random r = new Random();
        int[] index = new int[a];
        
        index[0] = a; 
        int track = 0;
        
        for(int j=0; j<b; j++)
        {
            int swap = r.nextInt(a-j)+j;
            
            int value1 = (index[swap] == 0) ? swap : index[swap];
            int value2 = (index[j] == 0) ? j : index[j];
            
            if(index[j] == a) {
                track = swap;
            }
            
            if(index[swap] == a) {
                track = j;
            }
            
            index[j] = value1;
            index[swap] = value2;
        }
        
        index[track] = 0;
        
        return prefix(index, b);
    }
    
    public static int[] prefix(int[] array, int n) {
        int[] prefix = new int[n];        
        for(int i=0; i<n; i++) {
            prefix[i] = array[i];
        }
        return prefix;
    }
    
    public static void printArray(int[] array) {
        System.out.print("[");
        for(int i=0; i<array.length; i++) {
            System.out.print((i<array.length-1)?(array[i]+","):(array[i]));
        }
        System.out.println("]");
    }
}
