package project.webapplication.erpsystem.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.webapplication.erpsystem.dto.EmployeeDto;
import project.webapplication.erpsystem.dto.InsuranceDto;
import project.webapplication.erpsystem.models.Insurance;
import project.webapplication.erpsystem.repository.InsuranceRepository;
import project.webapplication.erpsystem.service.EmployeeService;
import project.webapplication.erpsystem.service.InsuranceService;

import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    EmployeeService employeeService;
    @Override
    public List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public void addToInsuranceList() {
        List<EmployeeDto> employeeDtoList = employeeService.findAll();
        LocalDate dateNow = LocalDate.now();

        for (EmployeeDto employeeDto : employeeDtoList) {
            long days = ChronoUnit.DAYS.between(employeeDto.getJoiningDate(),dateNow);
            System.out.println(dateNow +" | " + employeeDto.getJoiningDate());
            System.out.println(days);
        }
    }
}
