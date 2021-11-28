package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import project.apicapstone.dto.allowance.PagingFormatAllowanceDto;
import project.apicapstone.dto.title.CreateTitleDto;
import project.apicapstone.dto.title.PagingFormatTitleDto;
import project.apicapstone.entity.Title;
import project.apicapstone.repository.TitleRepository;
import project.apicapstone.service.TitleService;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {
    private TitleRepository titleRepository;
    public TitleServiceImpl(TitleRepository titleRepository){
        this.titleRepository=titleRepository;
    }
    @Override
    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    @Override
    public Page<Title> findAllTitle(Pageable pageable) {
        return titleRepository.findAllAllTitle(pageable);
    }

    @Override
    public Object pagingFormat(Page<Title> titlePage) {
        PagingFormatTitleDto dto = new PagingFormatTitleDto();
        dto.setPageSize(titlePage.getSize());
        dto.setTotalRecordCount(titlePage.getTotalElements());
        dto.setPageNumber(titlePage.getNumber());
        dto.setRecords(titlePage.toList());
        return dto;
    }

    @Override
    public Title createTitle(CreateTitleDto dto) {
        Title newTitle = new Title();
        newTitle.setTitleId(dto.getTitleId());
        newTitle.setJobTitle(dto.getJobTitle());
        return titleRepository.save(newTitle);
    }

    @Override
    public boolean isExisted(String s) {
        return titleRepository.existsById(s);
    }
}
