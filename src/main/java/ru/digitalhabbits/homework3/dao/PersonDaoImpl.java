package ru.digitalhabbits.homework3.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import ru.digitalhabbits.homework3.domain.Person;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonDaoImpl
        implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Person findById(@Nonnull Integer id) {
        // TODO: NotImplemented
//        throw new NotImplementedException();
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        // TODO: NotImplemented
        throw new NotImplementedException();
    }

    @Override
    public Person update(Person entity) {
        // TODO: NotImplemented
        throw new NotImplementedException();
    }

    @Override
    public Person delete(Integer integer) {
        // TODO: NotImplemented
        throw new NotImplementedException();
    }
}
