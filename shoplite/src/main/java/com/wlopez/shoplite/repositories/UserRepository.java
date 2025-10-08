package com.wlopez.shoplite.repositories;
import com.wlopez.shoplite.models.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class UserRepository {
    private static final Map<String,User> users = new ConcurrentHashMap<>();
    static {
        // Default users: admin and a normal user
        users.put("admin@example.com", new User("admin@example.com","admin123","ADMIN"));
        users.put("user@example.com", new User("user@example.com","user123","USER"));
    }
    public static User findByEmail(String email){
        return users.get(email);
    }
}
