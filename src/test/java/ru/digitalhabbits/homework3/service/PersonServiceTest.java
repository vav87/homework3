package ru.digitalhabbits.homework3.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.digitalhabbits.homework3.dao.PersonDao;
import ru.digitalhabbits.homework3.domain.Person;
import ru.digitalhabbits.homework3.model.PersonRequest;
import ru.digitalhabbits.homework3.model.PersonResponse;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PersonServiceImpl.class)
class PersonServiceTest {

    @MockBean
    private PersonDao personDao;

    @Autowired
    private PersonService personService;

    @Test
    void findAllPersons() {
        // TODO: NotImplemented
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person().setId(1).setFirstName("A").setLastName("B").setAge(40);
        Person person2 = new Person().setId(2).setFirstName("C").setLastName("D").setAge(41);
        personList.add(person1);
        personList.add(person2);
        given(this.personDao.findAll()).willReturn(personList);

        List<PersonResponse> personResponseList = personService.findAllPersons();
        assertThat(personResponseList.size()).isEqualTo(personList.size());
    }

    @Test
    void getPerson() {
        // TODO: NotImplemented
        Person person = new Person().setId(1).setFirstName("A").setLastName("B").setAge(40);
        given(this.personDao.findById(anyInt())).willReturn(person);

        PersonResponse personResponse = personService.getPerson(1);
        assertThat(personResponse).isEqualToComparingOnlyGivenFields(person, "id", "age");
        assertThat(personResponse.getFullName()).isEqualTo(getFullName(person));
    }

    @Test
    void createPerson() {
        // TODO: NotImplemented
        Person person = new Person().setId(1).setFirstName("A").setLastName("B").setAge(40);
        given(this.personDao.update(any(Person.class))).willReturn(person);

        PersonRequest personRequest = new PersonRequest().setFirstName("A").setLastName("B").setAge(40);
        Integer personId = personService.createPerson(personRequest);
        assertThat(personId).isEqualTo(person.getId());
    }

    @Test
    void updatePerson() {
        // TODO: NotImplemented
        Person person = new Person().setId(1).setFirstName("A").setLastName("B").setAge(40);
        given(this.personDao.findById(anyInt())).willReturn(person);
        given(this.personDao.update(any(Person.class))).willReturn(person);

        PersonRequest personRequest = new PersonRequest().setFirstName("A").setLastName("B").setAge(40);
        PersonResponse personResponse = personService.updatePerson(1, personRequest);
        assertThat(personResponse).isEqualToComparingOnlyGivenFields(person, "id", "age");
        assertThat(personResponse.getFullName()).isEqualTo(getFullName(person));
    }

    @Test
    void deletePerson() {
        // TODO: NotImplemented
        Person person = new Person().setId(1).setFirstName("A").setLastName("B").setAge(40);
        given(this.personDao.findById(anyInt())).willReturn(person);
        given(this.personDao.delete(anyInt())).willReturn(person);

        personService.deletePerson(1);
        verify(personDao, times(1)).findById(anyInt());
        verify(personDao, times(1)).delete(anyInt());
    }

    private String getFullName(Person person) {
        String fullName = person.getFirstName().concat(" "+person.getLastName());
        if(person.getMiddleName() != null) {
            fullName = fullName.concat(" "+person.getMiddleName());
        }
        return fullName;
    }
}