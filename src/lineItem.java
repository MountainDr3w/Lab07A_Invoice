public class lineItem {
    private Product product;
    private int quantity;
    private double total;

    public lineItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.total = product.getUnitPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void calculateTotal() {
        this.total = product.getUnitPrice() * quantity;
    }
}
