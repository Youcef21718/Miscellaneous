public class print
{
    public static void main() {
        int length = 3;
        int base = 5;
        
        int[] index = new int[length];
        print(base, index);
        
        
        int[][][] array = 
        {
        {{0,1,2,3,4},{5,6,7,8,9},{10,11,12,13,14},{15,16,17,18,19}},
        {{20,21,22,23,24},{25,26,27,28,29},{30,31,32,33,34},{35,36,37,38,39}},
        {{40,41,42,43,44},{45,46,47,48,49},{50,51,52,53,54},{55,56,57,58,59}} 
        };
        
        System.out.print("[");
        for(int i=0; i<3; i++) {
            System.out.print("[");
            for(int j=0; j<4; j++) {
                System.out.print("[");
                for(int k=0; k<5; k++) {
                    System.out.print(array[i][j][k]);
                    System.out.print((k<4)?",":"");
                }
                System.out.print("]");
                System.out.print((j<3)?",":"");
            }
            System.out.print("]");
            System.out.print((i<2)?",":"");
        }
        System.out.println("]");
    }

    //wrapper function for print
    public static void print(int base, int[] index) {
        print(base, index, index.length);
    }
    
    public static void print(int base, int[] index, int length) {
        if(length==0) {
            printArray(index);
        }
        
        else {
            for(int i=0; i<base; i++) {
                index[index.length - length] = i;
                print(base, index, length-1);
            }
            System.out.println();
        }
    }
    
    public static void print(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                for(int k=0; k<n; k++) {
                    System.out.println(i+" "+j+" "+k);
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void printArray(int[] array) {
        System.out.print("[");
        for(int i=0; i<array.length; i++) {
            System.out.print((i<array.length-1)?(array[i]+","):(array[i]+"]\n"));
        }
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
    
    public static int getElement(int[] data, int[] shape, int[] perm, int[] index) {
        assert checkPerm(perm) : "not a permutation";
        
        int element = 0;
        for(int i=0; i<shape.length; i++) {
            element = element*shape[inv(perm)[i]] + index[inv(perm)[i]];
        }
        return data[element];
    }
    
    public static void setElement(int[] data, int[] shape, int[] perm, int[] index, int var) {
        assert checkPerm(perm) : "not a permutation";
        
        int element = 0;
        for(int i=0; i<shape.length; i++) {
            element = element*shape[inv(perm)[i]] + index[inv(perm)[i]];
        }
        
        data[element] = var;
    }
}
