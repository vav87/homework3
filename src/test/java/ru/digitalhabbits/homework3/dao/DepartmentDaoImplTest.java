package ru.digitalhabbits.homework3.dao;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalhabbits.homework3.domain.Department;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DepartmentDaoImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    void findById() {
        // TODO: NotImplemented
        Department department = new Department().setName("D1").setClosed(false);
        department = this.entityManager.merge(department);

        Department departmentFound = this.departmentDao.findById(department.getId());

        assertThat(departmentFound.getId()).isEqualTo(department.getId());
        assertThat(departmentFound.getName()).isEqualTo(department.getName());
    }

    @Test
    void findAll() {
        // TODO: NotImplemented
        Department department1 = new Department().setName("D1").setClosed(false);
        Department department2 = new Department().setName("D2").setClosed(false);
        List<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department2);

        department1 = this.entityManager.merge(department1);
        department2 = this.entityManager.merge(department2);

        List<Department> departmentList = this.departmentDao.findAll();

        assertThat(departmentList.size()).isEqualTo(departments.size());
        assertThat(departmentList.get(0)).isEqualToComparingFieldByField(department1);
        assertThat(departmentList.get(1)).isEqualToComparingFieldByField(department2);
    }

    @Test
    void update() {
        // TODO: NotImplemented
        Department department = new Department().setName("D1").setClosed(false);
        department = this.entityManager.merge(department);

        Department departmentUpdated = departmentDao.update(department);

        assertThat(departmentUpdated.getId()).isEqualTo(department.getId());
        assertThat(departmentUpdated.getName()).isEqualTo(department.getName());
    }

    @Test
    void delete() {
        // TODO: NotImplemented
        Department department = new Department().setName("D1").setClosed(false);
        department = this.entityManager.merge(department);

        Department departmentDeleted = departmentDao.delete(department.getId());

        assertThat(departmentDeleted.getId()).isEqualTo(department.getId());
        assertThat(departmentDeleted.getName()).isEqualTo(department.getName());
    }
}