package ru.mamreyan.businessprocessmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mamreyan.businessprocessmanager.address.Address;
import ru.mamreyan.businessprocessmanager.address.AddressRepository;
import ru.mamreyan.businessprocessmanager.bank.Bank;
import ru.mamreyan.businessprocessmanager.bank.BankRepository;
import ru.mamreyan.businessprocessmanager.employee.Employee;
import ru.mamreyan.businessprocessmanager.employee.EmployeeRepository;
import ru.mamreyan.businessprocessmanager.employee.Sex;
import ru.mamreyan.businessprocessmanager.position.Position;
import ru.mamreyan.businessprocessmanager.position.PositionRepository;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            EmployeeRepository employeeRepository,
            PositionRepository positionRepository,
            BankRepository bankRepository,
            AddressRepository addressRepository
    ) {
        return args -> {
            Iterable<Employee> employees = employeeRepository.findAll();

            if (employees instanceof Collection && ((Collection<Employee>) employees).size() <= 1) {
                employeeRepository.save(new Employee.EmployeeBuilder()
                                                .lastName("Bush")
                                                .firstName("George")
                                                .middleName("Walker")
                                                .sex(Sex.MALE)
                                                .birthDate(new GregorianCalendar(
                                                        1946,
                                                        Calendar.JULY,
                                                        6
                                                ))
                                                .employmentDate(new GregorianCalendar(
                                                        2001,
                                                        Calendar.JANUARY,
                                                        20
                                                ))
                                                .position(positionRepository.save(new Position("POTUS")))
                                                .salary(100000.99)
                                                .workPhone("123")
                                                .mobilePhone("321")
                                                .head(employeeRepository.save(new Employee.EmployeeBuilder()
                                                                                      .lastName("Potter")
                                                                                      .firstName("Harry")
                                                                                      // .middleName("James")
                                                                                      .sex(Sex.MALE)
                                                                                      .birthDate(new GregorianCalendar(
                                                                                              2000,
                                                                                              Calendar.JANUARY,
                                                                                              1
                                                                                      ))
                                                                                      .employmentDate(new GregorianCalendar(
                                                                                              2020,
                                                                                              Calendar.JANUARY,
                                                                                              1
                                                                                      ))
                                                                                      .position(positionRepository.save(new Position("Sorcerer")))
                                                                                      .salary(0)
                                                                                      .bank(bankRepository.save(new Bank(
                                                                                              "АО Альфа Бнак",
                                                                                              "345",
                                                                                              addressRepository.save(new Address(
                                                                                                      "Россия",
                                                                                                      "Новосибирская " +
                                                                                                      "обл.",
                                                                                                      "Новосибирск",
                                                                                                      "ул. Красный " +
                                                                                                      "Проспект",
                                                                                                      "д. 2",
                                                                                                      null
                                                                                              ))
                                                                                      )))
                                                                                      .build()))
                                                .bank(bankRepository.save(new Bank(
                                                        "ПАО Сбербанк",
                                                        "8943156",
                                                        addressRepository.save(new Address(
                                                                "Россия",
                                                                "Новосибирская обл.",
                                                                "Новосибирск",
                                                                "ул. Ленина",
                                                                "д. 1",
                                                                "кв. 1"
                                                        ))
                                                )))
                                                .build());

                employees = employeeRepository.findAll();
            }

            employees.forEach(employee -> log.info("Employees:\n" + employee));
        };
    }
}