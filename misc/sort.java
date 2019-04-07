import java.util.Random;
public class sort
{
    public static void main() {
        int[] input = random(10, 2, 5);
        countSort(input);
        mySort(input);
    }

    public static int min(int[] input) {
        int min = input[0];
        for(int i=1; i<input.length; i++) {
            if(input[i] < min) {
                min = input[i];
            }
        }
        return min;
    }
    
    public static int max(int[] input) {
        int max = input[0];
        for(int i=1; i<input.length; i++) {
            if(input[i] > max) {
                max = input[i];
            }
        }
        return max;
    }
    
    public static void mySort(int[] input) {
        int min = min(input);
        int max = max(input);
        
        //count[i]: num inputs = input[i]
        int[] count = new int[max-min+1];
        for(int i=0; i<input.length; i++) {
            count[input[i]-min]++;
        }
        print(count);
        
        int index=0;
        int[] output = new int[input.length];
        for(int i=0; i<count.length; i++) {
            for(int j=0; j<count[i]; j++) {
                output[index] = i+min;
                index++;
            }
        }
        print(output);
    }
    
    public static void countSort(int[] input) {
        int min = min(input);
        int max = max(input);
        
        print(input);
        
        //count[i]: num inputs = input[i]
        int[] count = new int[max-min+1];
        for(int i=0; i<input.length; i++) {
            count[input[i]-min]++;
        }
        print(count);
        
        //count[i]: num inputs < input[i]
        int total = 0;
        for(int i=0; i<count.length; i++) {
            int oldCount = count[i];
            count[i] = total;
            total += oldCount;
        }
        print(count);
        
        //First duplicate of input[i] placed at count[input[i]].
        //Subsequent duplicates are placed at higher values, hence count[input[i]]++
        //At end, count has shifted prefixes
        int[] output = new int[input.length];
        for(int i=0; i<input.length; i++) {
            output[count[input[i]-min]] = i;
            count[input[i]-min] += 1;
        }
        print(output);
        print(count);

    }
    
    public static int[] random(int length, int min, int max) {
        Random r = new Random();
        
        int[] random = new int[length];
        for(int i=0; i<length; i++) {
            random[i] = r.nextInt(max-min)+min;
        }
        return random;
    }
    
    public static void print(int[] array) {
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
