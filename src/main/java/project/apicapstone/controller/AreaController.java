package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.area.CreateAreaDto;
import project.apicapstone.dto.area.UpdateAreaDto;
import project.apicapstone.entity.Area;

import project.apicapstone.entity.Workplace;
import project.apicapstone.service.AreaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/area")
public class AreaController {
    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Area> areas = areaService.findAll();
        return ResponseHandler.getResponse(areas, HttpStatus.OK);
    }

    @PostMapping("/create-area")
    public Object createArea(@Valid @RequestBody CreateAreaDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Area createArea = areaService.createAccount(dto);

        return ResponseHandler.getResponse(createArea, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public Object deleteArea(@RequestParam(name = "id") String id) {
        areaService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateArea(@Valid @RequestBody UpdateAreaDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        areaService.updateArea(dto, dto.getAreaId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
    @GetMapping("/get-by-id/{id}")
    public Object findAreaById(@PathVariable("id") String id) {
        Area area = areaService.findAreaById(id);
        return ResponseHandler.getResponse(area, HttpStatus.OK);
    }
    @GetMapping("/search/{paramSearch}")
    public Object findAreaByNameOrId(@PathVariable String paramSearch) {
        List<Area> areaList = areaService.findAreaByNameOrId(paramSearch);
        if (areaList.isEmpty()) {
            return ResponseHandler.getErrors("Not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseHandler.getResponse(areaList, HttpStatus.OK);
    }
}
