package org.edu.mimo.ProjetJava.service;

import org.edu.mimo.ProjetJava.model.entity.EmployeeEntity;
import org.edu.mimo.ProjetJava.model.entity.EnclosureEntity;
import org.edu.mimo.ProjetJava.model.request.EmployeeRequest;
import org.edu.mimo.ProjetJava.model.request.EmployeeWithEnclosuresRequest;
import org.edu.mimo.ProjetJava.model.request.EnclosureRequest;
import org.edu.mimo.ProjetJava.repository.EmployeeRepository;
import org.edu.mimo.ProjetJava.repository.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EnclosureRepository enclosureRepository;

    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public EmployeeEntity createEmployee(EmployeeRequest employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employee.name());
        employeeEntity.setRole(employee.role());
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeRequest employeeDetails) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeEntity.setName(employeeDetails.name());
        employeeEntity.setRole(employeeDetails.role());
        return employeeRepository.save(employeeEntity);
    }

    public void deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(employeeEntity);
    }
    public EnclosureEntity addEnclosureToEmployee(Long employeeId, EnclosureRequest enclosureRequest) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            EmployeeEntity employee = employeeOptional.get();
            EnclosureEntity enclosure = new EnclosureEntity();
            enclosure.setName(enclosureRequest.name());
            enclosure.setLocation(enclosureRequest.location());
            enclosure.setEmployee(employee);
            return enclosureRepository.save(enclosure);

        } else {
            throw new RuntimeException("Employee not found");
        }
    }

}
