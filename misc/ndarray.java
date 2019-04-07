public class ndarray
{    
    public static void main() {        
        ////int[] shape0 = {};
        ////int[] data0 = createArray(shape0);
        
        ////int[] shape1 = {3,3};
        ////int[] data1 = createArray(shape1);
        
        ////int[] shape2 = {3,3};
        ////int[] data2 = createArray(shape2);
        
        ////printAll(data0, shape0);
        ////printAll(data1, shape1);
        ////printAll(data2, shape2);
        
        //mapReduce(data0, shape0, new int[]{}, data1, shape1, new int[]{0,0}, data1, shape2, new int[]{0,0});
        //printAll(data0, shape0);
        
        ////mapReduce(data0, shape0, new int[]{}, data1, shape1, new int[]{0,0});
        ////printAll(data0, shape0);
        
        int[] shape = new int[]{2,3,4};
        
        for(int i=0; i<shape[0]; i++) {
            for(int j=0; j<shape[1]; j++) {
                for(int k=0; k<shape[2]; k++) {
                    int[] index = new int[]{i,j,k};
                    int element = getElement(shape, index);
                    //System.out.println(element);
                    print(getIndex(element, shape));
                }
            }
        }
        
    }
    
    public static int[] getIndex(int element, int[] shape) {
        int[] index = new int[shape.length];
        for(int i=index.length-1; i>=0; i--) {
            index[i] = element%shape[i];
            element /= shape[i];
        }
        return index;
    }
    
    public static int getElement(int[] shape, int[] index) {        
        int element = 0;
        for(int i=0; i<shape.length; i++) {
            element *= shape[i];
            element += index[i];
        }
        return element;
    }
    
    public static int getElement(int[] data, int[] shape, int[] index) {
        assert checkShape(shape) : "invalid shape";
        assert checkData(data, shape) : "wrong shape for data";
        
        assert (index.length == shape.length) : "bad length";
        assert checkIndex(index, shape): "index out of bounds";
        
        int element = 0;
        for(int i=0; i<shape.length; i++) {
            element = element*shape[i] + index[i];
        }
        return data[element];
    }
    
    public static void setElement(int[] data, int[] shape, int[] index, int var) {
        assert checkShape(shape) : "invalid shape";
        assert checkData(data, shape) : "wrong shape for data";
        
        assert (index.length == shape.length) : "bad length";
        assert checkIndex(index, shape): "index out of bounds";
        
        int element = 0;
        for(int i=0; i<shape.length; i++) {
            element = element*shape[i] + index[i];
        }      
        data[element] = var;
    }
    
    public static void reduce(int[] data, int[] shape, int[] index, int var) {
        assert checkShape(shape) : "invalid shape";
        assert checkData(data, shape) : "wrong shape for data";
        
        assert (index.length == shape.length) : "bad length";
        assert checkIndex(index, shape): "index out of bounds";
        
        int element = 0;
        for(int i=0; i<shape.length; i++) {
            element = element*shape[i] + index[i];
        }      
        data[element] += var;
    }

    public static int length(int[] iter0, int[] iter1, int[] iter2) {
        int max = 0;
        
        for(int i=0; i<iter0.length; i++) {
            max = (iter0[i]>max) ? iter0[i] : max;
        }
        
        for(int i=0; i<iter1.length; i++) {
            max = (iter1[i]>max) ? iter1[i] : max;
        }
        
        for(int i=0; i<iter2.length; i++) {
            max = (iter2[i]>max) ? iter2[i] : max;
        }
        
        return max+1;
    }
    
    public static int[] shape(int[] shape0, int[] iter0, int[] shape1, int[] iter1, int[] shape2, int[] iter2) {        
        assert checkShape(shape0) : "invalid shape";
        assert checkShape(shape1) : "invalid shape";
        assert checkShape(shape2) : "invalid shape";
        
        assert checkIter(iter0) : "invalid iter";
        assert checkIter(iter1) : "invalid iter";
        assert checkIter(iter2) : "invalid iter";
        
        assert (iter0.length == shape0.length) : "iter-shape mismatch";
        assert (iter1.length == shape1.length) : "iter-shape mismatch";
        assert (iter2.length == shape2.length) : "iter-shape mismatch";
        
        int length = length(iter0, iter1, iter2);
        
        assert (length <= (iter0.length + iter1.length + iter2.length)) : "max is too big";
        
        int[] shape = new int[length];
        
        for(int i=0; i<iter0.length; i++) {
            if(shape[iter0[i]] == 0) {
                shape[iter0[i]] = shape0[i];
                length--;
            }
            
            else {
                //If an iterator is accessed twice, make sure that corresponding shape[i] has same value
                assert(shape[iter0[i]] == shape0[i]) : "unequal indices";
            }
        }
        
        for(int i=0; i<iter1.length; i++) {
            if(shape[iter1[i]] == 0) {
                shape[iter1[i]] = shape1[i];
                length--;
            }
            
            else {
                //If an iterator is accessed twice, make sure that corresponding shape[i] has same value
                assert(shape[iter1[i]] == shape1[i]) : "unequal indices";
            }
        }
        
        for(int i=0; i<iter2.length; i++) {
            if(shape[iter2[i]] == 0) {
                shape[iter2[i]] = shape2[i];
                length--;
            }
            
            else {
                //If an iterator is accessed twice, make sure that corresponding shape[i] has same value
                assert(shape[iter2[i]] == shape2[i]) : "unequal indices";
            }
        }
        
        //Checks whether every iterator from (0 to length) has been used
        assert (length == 0) : "unaccessed iterators";
        
        return shape;
    }
    
    public static int[] getSubset(int[] iter_n, int[] index) {          
        int[] index_n = new int[iter_n.length];
        for(int i=0; i<iter_n.length; i++) {
            index_n[i] = index[iter_n[i]];
        }
        return index_n;
    }
    
    public static void mapReduce(
    int[] data0, int[] shape0, int[] iter0,
    int[] data1, int[] shape1, int[] iter1,
    int[] data2, int[] shape2, int[] iter2) {
        int[] shape = shape(shape0, iter0, shape1, iter1, shape2, iter2);
        int[] index = new int[shape.length];
        
        mapReduce(
        data0, shape0, iter0,
        data1, shape1, iter1,
        data2, shape2, iter2,
        shape.length, shape, index);
    }
    
    public static void mapReduce(
    int[] data0, int[] shape0, int[] iter0,
    int[] data1, int[] shape1, int[] iter1,
    int[] data2, int[] shape2, int[] iter2,
    int length, int[] shape, int[] index) {        
        if(length==0) {
            int map = getElement(data1, shape1, getSubset(iter1, index)) * getElement(data2, shape2, getSubset(iter2, index));
            reduce(data0, shape0, getSubset(iter0, index), map);
        }
        
        else {
            for(int i=0; i<shape[index.length-length]; i++) {
                index[index.length - length] = i;
                
                mapReduce(
                data0, shape0, iter0,
                data1, shape1, iter1,
                data2, shape2, iter2,
                length-1, shape, index);
            }
        }
    }
    
    public static int length(int[] iter0, int[] iter1) {
        int max = 0;
        
        for(int i=0; i<iter0.length; i++) {
            max = (iter0[i]>max) ? iter0[i] : max;
        }
        
        for(int i=0; i<iter1.length; i++) {
            max = (iter1[i]>max) ? iter1[i] : max;
        }
        
        return max+1;
    }
    
    public static int[] shape(int[] shape0, int[] iter0, int[] shape1, int[] iter1) {        
        assert checkShape(shape0) : "invalid shape";
        assert checkShape(shape1) : "invalid shape";
        
        assert checkIter(iter0) : "invalid iter";
        assert checkIter(iter1) : "invalid iter";
        
        assert (iter0.length == shape0.length) : "iter-shape mismatch";
        assert (iter1.length == shape1.length) : "iter-shape mismatch";
        
        int length = length(iter0, iter1);
        
        assert (length <= (iter0.length + iter1.length)) : "max is too big";
        
        int[] shape = new int[length];
        
        for(int i=0; i<iter0.length; i++) {
            if(shape[iter0[i]] == 0) {
                shape[iter0[i]] = shape0[i];
                length--;
            }
            
            else {
                //If an iterator is accessed twice, make sure that corresponding shape[i] has same value
                assert(shape[iter0[i]] == shape0[i]) : "unequal indices";
            }
        }
        
        for(int i=0; i<iter1.length; i++) {
            if(shape[iter1[i]] == 0) {
                shape[iter1[i]] = shape1[i];
                length--;
            }
            
            else {
                //If an iterator is accessed twice, make sure that corresponding shape[i] has same value
                assert(shape[iter1[i]] == shape1[i]) : "unequal indices";
            }
        }
        
        //Checks whether every iterator from (0 to length) has been used
        assert (length == 0) : "unaccessed iterators";
        
        return shape;
    }
    
    public static void mapReduce(
    int[] data0, int[] shape0, int[] iter0,
    int[] data1, int[] shape1, int[] iter1) {
        int[] shape = shape(shape0, iter0, shape1, iter1);
        int[] index = new int[shape.length];
        
        mapReduce(
        data0, shape0, iter0,
        data1, shape1, iter1,
        shape.length, shape, index);
    }
    
    public static void mapReduce(
    int[] data0, int[] shape0, int[] iter0,
    int[] data1, int[] shape1, int[] iter1,
    int length, int[] shape, int[] index) {        
        if(length==0) {
            int map = getElement(data1, shape1, getSubset(iter1, index));
            reduce(data0, shape0, getSubset(iter0, index), map);
        }
        
        else {
            for(int i=0; i<shape[index.length-length]; i++) {
                index[index.length - length] = i;
                
                mapReduce(
                data0, shape0, iter0,
                data1, shape1, iter1,
                length-1, shape, index);
            }
        }
    }
       
    public static boolean checkShape(int[] shape) {
        for(int i=0; i<shape.length; i++) {
            if(shape[i] < 2) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkIndex(int[] index, int[] shape) {        
        for(int i=0; i<shape.length; i++) {
            if(!(0<=index[i] && index[i]<shape[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkData(int[] data, int[] shape) {
        int length = 1;
        for(int i=0; i<shape.length; i++) {
            length *= shape[i];
        }        
        return (length == data.length);
    }
    
    public static boolean checkIter(int[] iter_n) {
        for(int i=0; i<iter_n.length; i++) {
            if(iter_n[i]<0) {
                return false;
            }
        }
        return true;
    }
    
    //wrapper function for print
    public static void printAll(int[] data, int[] shape) {
        assert checkShape(shape) : "invalid shape";
        assert checkData(data, shape) : "wrong shape for data";
        
        int[] index = new int[shape.length];
        printAll(data, shape, index, index.length);
        
        System.out.println();
    }
    
    public static void printAll(int[] data, int[] shape, int[] index, int length) {        
        if(length==0) {
            //print(index);
            //System.out.println(getElement(data, shape, perm, index));
            System.out.print(getElement(data, shape, index));
        }
        
        else {
            System.out.print("[");
            for(int i=0; i<shape[index.length-length]; i++) {
                index[index.length - length] = i;
                printAll(data, shape, index, length-1);
                System.out.print((i<shape[index.length-length]-1)?",":"");
            }
            //System.out.println();
            System.out.print("]");
        }
    }
    
    public static void print(int[] array) {
        System.out.print("[");
        for(int i=0; i<array.length; i++) {
            System.out.print((i<array.length-1)?(array[i]+","):(array[i]));
        }
        System.out.println("]");
    }
    
    public static int[] createArray(int[] shape) {
        assert checkShape(shape) : "invalid shape";
        
        int length = 1;
        for(int i=0; i<shape.length; i++) {
            length *= shape[i];
        }
        
        int[] array = new int[length];
        for(int i=0; i<length; i++) {
            array[i] = i;
        }
        return array;
    }
}