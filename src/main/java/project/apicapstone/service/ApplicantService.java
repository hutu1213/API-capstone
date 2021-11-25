package project.apicapstone.service;

import project.apicapstone.dto.applicant.CreateApplicantDto;
import project.apicapstone.entity.Applicant;

import java.util.List;

public interface ApplicantService {
    List<Applicant> findAll();

    Applicant createApplicant(CreateApplicantDto dto);
}
