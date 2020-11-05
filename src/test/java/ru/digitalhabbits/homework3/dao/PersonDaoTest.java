package ru.digitalhabbits.homework3.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalhabbits.homework3.domain.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PersonDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonDao personDao;

    @Test
    void findById() {
        // TODO: NotImplemented
        Person person = new Person().setFirstName("A").setMiddleName("B").setLastName("C").setAge(40);
        person = this.entityManager.merge(person);

        Person personFound = this.personDao.findById(person.getId());

        assertThat(personFound).isEqualToComparingFieldByField(person);
    }

    @Test
    void findAll() {
        // TODO: NotImplemented
        Person person1 = new Person().setFirstName("A").setMiddleName("B").setLastName("C").setAge(40);
        Person person2 = new Person().setFirstName("D").setMiddleName("E").setLastName("F").setAge(41);
        person1 = this.entityManager.merge(person1);
        person2 = this.entityManager.merge(person2);

        List<Person> personList = this.personDao.findAll();

        assertThat(personList.size()).isEqualTo(Arrays.asList(person1, person2).size());
        assertThat(personList.get(0)).isEqualToComparingFieldByField(person1);
        assertThat(personList.get(1)).isEqualToComparingFieldByField(person2);
    }

    @Test
    void update() {
        // TODO: NotImplemented
        Person person = new Person().setFirstName("A").setMiddleName("B").setLastName("C").setAge(40);
        person = this.entityManager.merge(person);

        Person personUpdated = this.personDao.update(person);

        assertThat(personUpdated).isEqualToComparingFieldByField(person);
    }

    @Test
    void delete() {
        // TODO: NotImplemented
        Person person = new Person().setFirstName("A").setMiddleName("B").setLastName("C").setAge(40);
        person = this.entityManager.merge(person);

        Person personDeleted = this.personDao.delete(person.getId());

        assertThat(personDeleted).isEqualToComparingFieldByField(person);
    }
}