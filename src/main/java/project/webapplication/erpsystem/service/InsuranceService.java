package project.webapplication.erpsystem.service;

import project.webapplication.erpsystem.dto.InsuranceDto;
import project.webapplication.erpsystem.models.Insurance;

import java.util.List;

public interface InsuranceService {
    List<Insurance> findAll();

    void addToInsuranceList();
}
