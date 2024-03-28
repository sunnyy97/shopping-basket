import java.util.*;

// 상품 클래스
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// 장바구니 클래스
class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    // 장바구니에 담긴 상품들 출력
    public void showItems() {
        System.out.println("장바구니 목록:");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }

    // 상품을 장바구니에 추가
    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
        System.out.println(quantity + " " + product.getName() + "(s)가 장바구니에 추가됐습니다.");
    }

    // 상품을 장바구니에서 제거
    public void removeProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            if (currentQuantity > quantity) {
                items.put(product, currentQuantity - quantity);
                System.out.println(quantity + " " + product.getName() + "(s)가 장바구니에서 제거됐습니다.");
            } else if (currentQuantity == quantity) {
                items.remove(product);
                System.out.println(quantity + " " + product.getName() + "(s)가 장바구니에 추가됐습니다.");
            } else {
                System.out.println("Not enough quantity of " + product.getName() + " in the cart.");
            }
        } else {
            System.out.println(product.getName() + " is not in the cart.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // 상품 목록 생성
        Set<Product> productSet = new HashSet<>();
        productSet.add(new Product("Milk", 2.5));
        productSet.add(new Product("Apple", 1.0));

        // 상품 목록 확인
        System.out.println("현재 장바구니 목록:");
        for (Product product : productSet) {
            System.out.println(product.getName() + " : " + product.getPrice());
        }

        // 장바구니 생성
        Cart myCart = new Cart();

        // 상품을 장바구니에 추가
        myCart.addProduct(new Product("Milk", 2.5), 2);
        myCart.addProduct(new Product("Apple", 1.0), 3);

        // 상품을 장바구니에서 제거
        myCart.removeProduct(new Product("Milk", 2.5), 1);

        // 장바구니에 현재 담긴 상품들을 출력 (상품이름, 각 상품의 갯수)
        myCart.showItems();
    }
}
