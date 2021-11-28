package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.allowance.CreateAllowanceDto;
import project.apicapstone.entity.Allowance;

import java.util.List;

public interface AllowanceService {
    List<Allowance> findAll();

    boolean isExisted(String s);

    Allowance createAllowance(CreateAllowanceDto dto);

    Page<Allowance> findAllAllowance(Pageable pageable);

    Object pagingFormat(Page<Allowance> allowancePage);
}
