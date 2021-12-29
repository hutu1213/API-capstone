package project.apicapstone.service;

import project.apicapstone.dto.area.CreateAreaDto;
import project.apicapstone.entity.Area;

import java.util.List;

public interface AreaService {
    List<Area> findAll();

    Area createAccount(CreateAreaDto dto);
}
