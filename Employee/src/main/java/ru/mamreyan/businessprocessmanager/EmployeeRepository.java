package ru.mamreyan.businessprocessmanager;

import org.springframework.data.repository.CrudRepository;

interface EmployeeRepository extends CrudRepository<Employee, Long> {
}