package project.webapplication.erpsystem.service.imple;

import org.modelmapper.ModelMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    EmployeeService employeeService;

    ModelMapper modelMapper= new ModelMapper();

    public InsuranceServiceImpl() {
    }

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, EmployeeService employeeService, ModelMapper modelMapper) {
        this.insuranceRepository = insuranceRepository;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<InsuranceDto> findAll() {
        return insuranceRepository.findAll().stream()
                .map(insurance -> modelMapper.map(insurance,InsuranceDto.class))
                .collect(Collectors.toList());
    }


//    @Override
//    public List<EmployeeDto> findEmployeeInsurance() {
//        return insuranceRepository.findEmployeeInsurance().stream()
//                .map(employee -> modelMapper.map(employee,EmployeeDto.class))
//                .collect(Collectors.toList());
//    }

//    @Override
//    public List<EmployeeDto> addToInsuranceList() {
//        List<EmployeeDto> employeeDtoList = employeeService.findAll();
//        List<EmployeeDto> employeeDtoInsuranList = findEmployeeInsurance();
//        LocalDate dateNow = LocalDate.now();
//
//        for (EmployeeDto employeeDto : employeeDtoList) {
//            if (!employeeDtoInsuranList.contains(employeeDto)) {
//                long days = ChronoUnit.DAYS.between(employeeDto.getJoiningDate(), dateNow);
//                if (days >30){
//                    employeeDtoInsuranList.add(employeeDto);
//                }
//            }
//        }
//        return employeeDtoInsuranList;
//    }
}
