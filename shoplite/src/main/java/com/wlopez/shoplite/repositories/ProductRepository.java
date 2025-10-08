package com.wlopez.shoplite.repositories;
import com.wlopez.shoplite.models.Product;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
public class ProductRepository {
    private static final Map<Integer,Product> items = new LinkedHashMap<>();
    private static final AtomicInteger seq = new AtomicInteger(0);
    static {
        // sample products
        save(new Product(nextId(), "Camiseta", 12.5));
        save(new Product(nextId(), "Pantalón", 25.0));
        save(new Product(nextId(), "Gorra", 8.0));
        save(new Product(nextId(), "Zapatos", 45.0));
        save(new Product(nextId(), "Lentes", 15.0));
    }
    public static int nextId(){ return seq.incrementAndGet(); }
    public static void save(Product p){ items.put(p.getId(), p); }
    public static List<Product> findAll(){ return new ArrayList<>(items.values()); }
    public static int count(){ return items.size(); }
}
