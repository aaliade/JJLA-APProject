package models;

//Interface to handle basic CRUD operations.
public interface UserAccount<U> {
    boolean create();
    U find(String username);
    boolean delete(U user);
}

