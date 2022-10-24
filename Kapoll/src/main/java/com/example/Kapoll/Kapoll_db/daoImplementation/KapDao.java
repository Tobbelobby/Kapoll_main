package com.example.Kapoll.Kapoll_db.daoImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface KapDao<T> {

    T get(Long id);

    List<T> getAll();

    void save(T t);

    public void addAndSave(T t);

    void delete(T t);

    void removeFromList(T t);

    boolean exist(Long id);



}
