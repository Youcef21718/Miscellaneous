public class catalan
{
    public static void main() {
        parenList(4);
    }
    
    public static void parenList(int n) {
        char[] array = new char[2*n];
        parenList(array, n, n, 0);
    }
    
    public static void parenList(char[] array, int left, int right, int i) {        
        if(i == array.length) {
            printArray(array);
        }
        else {
            if(left>0) {
                array[i] = '(';
                parenList(array, left-1, right, i+1);
                array[i] = '\0';
            }
        
            if(right>left) {
                array[i] = ')';
                parenList(array, left, right-1, i+1);
                array[i] = '\0';
            }
        }
        
    }
    
    public static boolean checkParen(char[] array) {
        int diff = 0;
        for(int i=0; i<array.length; i++) {
            assert (array[i]=='(' || array[i]==')');
            
            if(array[i]=='(') {
                diff += 1;
            }
            
            if(array[i]==')') {
                diff -= 1;
            }
            
            if(diff < 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void printArray(char[] array) {
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }
}
