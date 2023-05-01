public class MyHashTable<K,V> {
    static class HashNode<K,V> {
        public HashNode<K,V> next;
        K key;
        V value;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    HashNode<K,V>[] chainArray;
    private final int M = 11;
    private int size; 
    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int initialSize) {
        if (initialSize <= 0)
            throw new IllegalArgumentException("Illegal array size");
        chainArray = new HashNode[initialSize];
    }
    private int hash(K key) {
        return Math.abs(key.hashCode() % chainArray.length);
    }
    private void expandTable() {
        HashNode<K,V>[] newtable = new HashNode[chainArray.length*2];
        size = 0;
        for (int i = 0; i < chainArray.length; i++) {
            HashNode<K,V> list = chainArray[i];
            while (list != null) {
                HashNode<K,V> next = list.next;
                int hash = (Math.abs(list.key.hashCode())) % newtable.length;
                list.next = newtable[hash];
                newtable[hash] = list;
                list = next;
                size++;
            }
        }
        chainArray = newtable;
    }
    public void put(K key, V value) {
        assert key != null : "The key must be non-null";
        int bucket = hash(key);
        HashNode<K,V> list = chainArray[bucket];
        while (list != null) {
            if (list.key.equals(key))
                break;
            list = list.next;
        }
        if (list != null) {
            list.value = value;
        } else {
            if (size >= 0.75* chainArray.length) {
                expandTable();
                bucket = hash(key);
            }
            HashNode<K,V> newNode = new HashNode<>(key, value);
            newNode.next = chainArray[bucket];
            chainArray[bucket] = newNode;
            size++;
        }
    }
    public V get(K key) {
        int bucket = hash(key);

        HashNode<K,V> list = chainArray[bucket];
        while (list != null) {
            if (list.key.equals(key))
                return list.value;
            list = list.next;
        }
        return null;
    }
    public boolean containsKey(K key) {

        int bucket = hash(key);

        HashNode<K,V> list = chainArray[bucket];
        // check if there is needed key
        while (list != null) {
            if (list.key.equals(key))
                return true;
            list = list.next;
        }

        return false;
    }
    public K getKey(V value) {
        for (HashNode<K,V> node : chainArray) {
            HashNode<K,V> current = node;
            // check if there is needed key
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }
    public int size() {
        return size;
    }

    public HashNode<K,V>[] getChainArray() {
        return chainArray;
    }
    public void clear() {
        for (int i = 0; i < chainArray.length; i++) {
            chainArray[i] = null;
        }
        size = 0;
    }
    public void remove(K key) {
        int bucket = hash(key);
        HashNode<K,V> list = chainArray[bucket];
        HashNode<K,V> prev = null;
        while (list != null) {
            if (list.key.equals(key)) {
                if (prev == null) {
                    chainArray[bucket] = list.next;
                } else {
                    prev.next = list.next;
                }
                size--; 
                return;
            }
            prev = list;
            list = list.next;
        }
    }
}