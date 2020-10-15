package ru.digitalhabbits.homework3.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import ru.digitalhabbits.homework3.model.PersonRequest;
import ru.digitalhabbits.homework3.model.PersonResponse;

import javax.annotation.Nonnull;
import java.util.List;

@Service
public class PersonServiceImpl
        implements PersonService {

    @Nonnull
    @Override
    public List<PersonResponse> findAllPersons() {
        // TODO: NotImplemented: получение информации о всех людях во всех отделах
        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    public PersonResponse getPerson(@Nonnull Integer id) {
        // TODO: NotImplemented: получение информации о человеке. Если не найдено, отдавать 404:NotFound
        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    public Integer createPerson(@Nonnull PersonRequest request) {
        // TODO: NotImplemented: создание новой записи о человеке
        throw new NotImplementedException();
    }

    @Nonnull
    @Override
    public PersonResponse updatePerson(@Nonnull Integer id, @Nonnull PersonRequest request) {
        // TODO: NotImplemented: обновление информации о человеке. Если не найдено, отдавать 404:NotFound
        throw new NotImplementedException();
    }

    @Override
    public void deletePerson(@Nonnull Integer id) {
        // TODO: NotImplemented: удаление информации о человеке и удаление его из отдела. Если не найдено, ничего не делать
        throw new NotImplementedException();
    }
}
