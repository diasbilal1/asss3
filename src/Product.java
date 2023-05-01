public class Product {
    private int first_Id;
    private int sec_id;
    public Product(int brandId, int modelId) {
        this.first_Id = brandId;
        this.sec_id = modelId;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return first_Id == other.first_Id && sec_id == other.sec_id;
    }

    @Override
    public int hashCode() {
        int h = 17;
        h = 31 * h + first_Id;
        h = 31 * h + sec_id;
        return h;
    }
}
