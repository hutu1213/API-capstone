package project.apicapstone.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;

import project.apicapstone.dto.workplace.CreateWorkplaceDto;
import project.apicapstone.dto.workplace.UpdateWorkplaceDto;

import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Workplace;

import project.apicapstone.service.WorkplaceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/workplace")
public class WorkplaceController {
    private WorkplaceService workplaceService;

    public WorkplaceController(WorkplaceService workplaceService) {
        this.workplaceService = workplaceService;
    }

    @GetMapping
    public Object findAllWorkplace(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Workplace> workplacePage = workplaceService.findAllWorkplace(pageable);
        return ResponseHandler.getResponse(workplaceService.pagingFormat(workplacePage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Workplace> workplaces = workplaceService.findAll();
        return ResponseHandler.getResponse(workplaces, HttpStatus.OK);
    }

    @PostMapping("/create-workplace")
    public Object createWorkplace(@Valid @RequestBody CreateWorkplaceDto dto,
                                  BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Workplace createWorkplace = workplaceService.createWorkplace(dto);

        return ResponseHandler.getResponse(createWorkplace, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public Object deleteWorkplace(@RequestParam(name = "id") String id) {
        workplaceService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateWorkplace(@Valid @RequestBody UpdateWorkplaceDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        workplaceService.updateWorkplace(dto, dto.getWorkplaceId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public Object findWorkplaceById(@PathVariable("id") String id) {
        Workplace workplace = workplaceService.findWorkplaceById(id);
        return ResponseHandler.getResponse(workplace, HttpStatus.OK);
    }

    @GetMapping("/get-by-subarea/{id}")
    public Object getBySubarea(@PathVariable("id") String id) {
        List<Workplace> workplaceLis = workplaceService.getBySubarea(id);
        return ResponseHandler.getResponse(workplaceLis, HttpStatus.OK);
    }

    @GetMapping("/search/{paramSearch}")
    public Object findWorkplaceByNameOrId(@PathVariable String paramSearch) {
        List<Workplace> workplaceList = workplaceService.findWorkplaceByNameOrId(paramSearch);
        if (workplaceList.isEmpty()) {
            return ResponseHandler.getErrors("Not found", HttpStatus.NOT_FOUND);
        }
        return ResponseHandler.getResponse(workplaceList, HttpStatus.OK);
    }
}
