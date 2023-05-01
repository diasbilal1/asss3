import java.util.Random;
public class Main {
    public static void main(String[] args) {
        MyHashTable<Testing, Product> table = new MyHashTable<>();
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            Testing key = new Testing(rand.nextInt(10000));
            Product value = new Product(rand.nextInt(100), rand.nextInt(100));
            table.put(key, value);
        }
        MyHashTable.HashNode<Testing, Product>[] chainArray = table.getChainArray();
        for (int i = 0; i < chainArray.length; i++) {
            MyHashTable.HashNode<Testing, Product> list = chainArray[i];
            int count = 0;
            while (list != null) {
                count++;
                list = list.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
        System.out.println("key-value pairs in the table is " + table.size());
        for (int i = 0; i < 100; i++) {
            Testing key = new Testing(i);
            table.remove(key);
        }
        table.clear();
    }
}
