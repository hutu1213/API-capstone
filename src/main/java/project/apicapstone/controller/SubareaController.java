package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;

import project.apicapstone.dto.subarea.CreateSubareaDto;
import project.apicapstone.dto.subarea.UpdateSubareaDto;
import project.apicapstone.entity.Subarea;

import project.apicapstone.entity.Workplace;
import project.apicapstone.service.SubareaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subarea")
public class SubareaController {
    private SubareaService subareaService;

    public SubareaController(SubareaService subareaService) {
        this.subareaService = subareaService;
    }
    @GetMapping
    public Object findAllSubarea(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Subarea> subareaPage = subareaService.findAllSubarea(pageable);
        return ResponseHandler.getResponse(subareaService.pagingFormat(subareaPage), HttpStatus.OK);
    }
    @GetMapping("/get-all")
    public Object findAll() {
        List<Subarea> workplaces = subareaService.findAll();
        return ResponseHandler.getResponse(workplaces, HttpStatus.OK);
    }

    @PostMapping("/create-subarea")
    public Object createSubarea(@Valid @RequestBody CreateSubareaDto dto,
                                BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Subarea createSubarea = subareaService.createSubarea(dto);

        return ResponseHandler.getResponse(createSubarea, HttpStatus.CREATED);
    }
    @DeleteMapping()
    public Object deleteSubarea(@RequestParam(name = "id") String id) {
        subareaService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateSubarea(@Valid @RequestBody UpdateSubareaDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        subareaService.updateSubarea(dto, dto.getSubareaId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
    @GetMapping("/get-by-id/{id}")
    public Object findSubareaById(@PathVariable("id") String id) {
        Subarea subarea = subareaService.findSubareaById(id);
        return ResponseHandler.getResponse(subarea, HttpStatus.OK);
    }
    @GetMapping("/search/{paramSearch}")
    public Object findSubareaByNameOrId(@PathVariable String paramSearch) {
        List<Subarea> subareaList = subareaService.findSubareaByNameOrId(paramSearch);
        if (subareaList.isEmpty()) {
            return ResponseHandler.getErrors("Not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseHandler.getResponse(subareaList, HttpStatus.OK);
    }
    @GetMapping("/get-by-area/{id}")
    public Object getByAreaId(@PathVariable("id") String id) {
      List<Subarea> subareaList = subareaService.findSubareaByAreaId(id);
        return ResponseHandler.getResponse(subareaList, HttpStatus.OK);
    }

}
