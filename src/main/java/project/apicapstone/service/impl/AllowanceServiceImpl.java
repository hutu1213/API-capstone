package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.allowance.CreateAllowanceDto;
import project.apicapstone.dto.allowance.PagingFormatAllowanceDto;
import project.apicapstone.dto.employee.PagingFormatEmployeeDto;
import project.apicapstone.entity.Allowance;
import project.apicapstone.repository.AllowanceRepository;
import project.apicapstone.service.AllowanceService;

import java.util.List;

@Service
public class AllowanceServiceImpl implements AllowanceService {
    private AllowanceRepository allowanceRepository;

    public AllowanceServiceImpl(AllowanceRepository allowanceRepository) {
        this.allowanceRepository = allowanceRepository;
    }

    @Override
    public List<Allowance> findAll() {
        return allowanceRepository.findAll();
    }

    @Override
    public boolean isExisted(String s) {
        return allowanceRepository.existsById(s);
    }

    @Override
    public Allowance createAllowance(CreateAllowanceDto dto) {
        Allowance newAllowance = new Allowance();
        newAllowance.setAllowanceId(dto.getAllowanceId());
        newAllowance.setAllowanceName(dto.getAllowanceName());
        newAllowance.setType(dto.getType());
        return allowanceRepository.save(newAllowance);
    }

    @Override
    public Page<Allowance> findAllAllowance(Pageable pageable) {
        return allowanceRepository.findAllAllowance(pageable);
    }

    @Override
    public Object pagingFormat(Page<Allowance> allowancePage) {
        PagingFormatAllowanceDto dto = new PagingFormatAllowanceDto();
        dto.setPageSize(allowancePage.getSize());
        dto.setTotalRecordCount(allowancePage.getTotalElements());
        dto.setPageNumber(allowancePage.getNumber());
        dto.setRecords(allowancePage.toList());
        return dto;
    }
}
