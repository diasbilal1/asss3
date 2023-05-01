public class Testing {
    private int key;
    public Testing(int key) {
        this.key = key;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Testing)) {
            return false;
        }
        Testing other = (Testing) obj;
        return key == other.key;
    }
    @Override
    public int hashCode() {
        int h = 0;
        for (int i = 0; i < 4; i++) {
            h ^= ((key >>> (i * 8)) & 0xff);
        }
        return h;
    }
}
