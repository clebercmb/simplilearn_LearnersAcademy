package com.example.modules.classes.adapter.out.persistence;


import com.example.modules.classes.domain.Class;

import java.util.List;
import java.util.Optional;

public interface ClassDao {

    public Class add(Class student);

    public Optional<Class> get(int id);

    public List<Class> get();

    public Class update(Class student);

    public void delete(int id);

}
