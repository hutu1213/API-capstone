package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.account.PagingFormatAccountDto;
import project.apicapstone.dto.position.CreatePositionDto;
import project.apicapstone.dto.position.PagingFormatPositionDto;
import project.apicapstone.entity.Position;
import project.apicapstone.repository.PositionRepository;
import project.apicapstone.service.PositionService;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Page<Position> findAllPosition(Pageable pageable) {
        return positionRepository.findAllPosition(pageable);
    }

    @Override
    public Object pagingFormat(Page<Position> positionPage) {
        PagingFormatPositionDto dto = new PagingFormatPositionDto();
        dto.setPageSize(positionPage.getSize());
        dto.setTotalRecordCount(positionPage.getTotalElements());
        dto.setPageNumber(positionPage.getNumber());
        dto.setRecords(positionPage.toList());
        return dto;
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position createPosition(CreatePositionDto dto) {
        Position newPosition =new Position();
        newPosition.setPositionId(dto.getPositionId());
        newPosition.setPositionName(dto.getPositionName());
        return positionRepository.save(newPosition);
    }

    @Override
    public boolean isExisted(String s) {
        return positionRepository.existsById(s);
    }
}
