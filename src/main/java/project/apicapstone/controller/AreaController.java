package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.area.CreateAreaDto;
import project.apicapstone.dto.area.UpdateAreaDto;
import project.apicapstone.entity.Area;

import project.apicapstone.service.AreaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/area")
public class AreaController {
    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }
    @GetMapping
    public Object findAllArea(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Area> areaPage = areaService.findAllArea(pageable);
        return ResponseHandler.getResponse(areaService.pagingFormat(areaPage), HttpStatus.OK);
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
