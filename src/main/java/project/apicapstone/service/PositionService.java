package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.position.CreatePositionDto;
import project.apicapstone.entity.Position;

import java.util.List;

public interface PositionService {
    Page<Position> findAllPosition(Pageable pageable);

    Object pagingFormat(Page<Position> positionPage);

    List<Position> findAll();

    Position createPosition(CreatePositionDto dto);

    boolean isExisted(String s);
}
