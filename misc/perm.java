//https://blog.merovius.de/2014/08/12/applying-permutation-in-constant.html
//http://www.christopia.net/blog/lazy-shuffled-list-generator
//https://www.i-programmer.info/programming/theory/2744-how-not-to-shuffle-the-kunth-fisher-yates-algorithm.html
//https://stackoverflow.com/questions/10054732/create-a-random-permutation-of-1-n-in-constant-space
//https://en.wikipedia.org/wiki/Pseudorandom_permutation
//http://blog.notdot.net/2007/9/Damn-Cool-Algorithms-Part-2-Secure-permutations-with-block-ciphers
public class perm
{
    public static void main() {
        int[] perm = new int[]{2,13,1,5,3,15,14,12,8,10,4,19,16,11,9,7,18,6,17,0};
        int[] vals = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};        
        
        permute1(vals, perm);
    }

    public static void permute1(int[] vals, int[] perm) {
        int temp;
        for(int i=0; i<perm.length; i++) {
            while(perm[i]<perm.length) {
                //swap i and perm[i]
                temp = perm[i];
                perm[i] += perm.length;
                i = temp;
                
                //swap vals[i] and vals[perm[i]]
                if(perm[i]<perm.length) {
                    temp = vals[i];
                    vals[i] = vals[perm[i]];
                    vals[perm[i]] = temp;
                }           
            }
            perm[i] -= perm.length;
        }
        print(vals);
        print(perm);
    }

    public static void permute2(int[] vals, int[] perm) {
        int temp;
        for(int i=0; i<perm.length; i++) {
            while(i!=perm[i]) {
                //swap i and perm[i]
                temp = perm[i];
                perm[i] = i;
                i = temp;
                
                //swap vals[i] and vals[perm[i]]
                if(i!=perm[i]) {
                    temp = vals[i];
                    vals[i] = vals[perm[i]];
                    vals[perm[i]] = temp;
                }
            }
        }
        print(vals);
        print(perm);
    }

    public static void print(int[] array) {
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
