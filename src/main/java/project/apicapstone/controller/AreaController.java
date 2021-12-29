package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.area.CreateAreaDto;
import project.apicapstone.entity.Area;
import project.apicapstone.service.AreaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/area")
public class AreaController {
    private AreaService areaService;
    public AreaController(AreaService areaService){
        this.areaService=areaService;
    }
    @GetMapping("/get-all")
    public Object findAll() {
        List<Area> areas = areaService.findAll();
        return ResponseHandler.getResponse(areas, HttpStatus.OK);
    }

    @PostMapping("/create-account")
    public Object createArea(@Valid @RequestBody CreateAreaDto dto,
                                BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Area createArea = areaService.createAccount(dto);

        return ResponseHandler.getResponse(createArea, HttpStatus.CREATED);
    }
}
