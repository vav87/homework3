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
        Person p = entityManager.find(Person.class, id);
        return p;
    }

    @Override
    public List<Person> findAll() {
        // TODO: NotImplemented
        //throw new NotImplementedException();
        List<Person> allPersons = entityManager.createQuery("Select p from Person p", Person.class).getResultList();
        return allPersons;
    }

    @Override
    public Person update(Person entity) {
        // TODO: NotImplemented
        //throw new NotImplementedException();
        entityManager.persist(entity);
        return  entity;
    }

    @Override
    public Person delete(Integer integer) {
        // TODO: NotImplemented
        //throw new NotImplementedException();
        Person personToRemove = this.findById(integer);
        entityManager.remove(personToRemove);
        return personToRemove;
    }
}
