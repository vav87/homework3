package ru.digitalhabbits.homework3.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import ru.digitalhabbits.homework3.domain.Department;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DepartmentDaoImpl
        implements DepartmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department findById(@Nonnull Integer integer) {
        // TODO: NotImplemented
        //throw new NotImplementedException();
        return entityManager.find(Department.class, integer);
    }

    @Override
    public List<Department> findAll() {
        // TODO: NotImplemented
        //throw new NotImplementedException();
        List<Department> allDepartments = entityManager.createQuery("Select d from Department d", Department.class).getResultList();
        return allDepartments;
    }

    @Override
    public Department update(Department entity) {
        // TODO: NotImplemented
        //throw new NotImplementedException();
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Department delete(Integer integer) {
        // TODO: NotImplemented
        //throw new NotImplementedException();
        Department departmentToRemove = this.findById(integer);
        entityManager.remove(departmentToRemove);
        return departmentToRemove;
    }
}
