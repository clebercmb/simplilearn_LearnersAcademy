package com.example.modules.classes.adapter.out.persistence;

import com.example.modules.classes.domain.Class;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClassDaoImpl implements ClassDao {

    private static List<Class> aClasses = null;
    private static int index = 0;

    public ClassDaoImpl() {
        if(aClasses == null) {
            aClasses = new ArrayList<>();
        }
    }

    @Override
    public Class add(Class aClass) {
        aClass.setId(index+1);
        index++;
        aClasses.add(aClass);
        return aClass;
    }

    @Override
    public Optional<Class> get(int id) {

        return aClasses.stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public List<Class> get() {

        return aClasses;
    }

    @Override
    public Class update(Class classUpdated) {
        Optional<Class> hasClass = aClasses.stream().filter(s->s.getId() == classUpdated.getId()).findFirst();
        if (hasClass.isPresent()) {
            Class aClass = hasClass.get();
            aClass.setName(classUpdated.getName());
        }
        return null;
    }

    @Override
    public void delete(int id) {

        aClasses.removeIf(s->s.getId() == id);

    }
}
