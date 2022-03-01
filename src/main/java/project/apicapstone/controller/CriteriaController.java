package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.common.util.ScanApplicant;
import project.apicapstone.dto.criteria.CreateCriteriaDto;
import project.apicapstone.dto.criteria.UpdateCriteriaDto;
import project.apicapstone.entity.Criteria;

import project.apicapstone.service.CriteriaService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/criteria")
public class CriteriaController {
    private CriteriaService criteriaService;
private ScanApplicant scanApplicant;
    public CriteriaController(CriteriaService criteriaService,ScanApplicant scanApplicant) {
        this.criteriaService = criteriaService;
        this.scanApplicant=scanApplicant;
    }

    @GetMapping
    public Object findAllCriteria(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Criteria> criteriaPage = criteriaService.findAll(pageable);
        return ResponseHandler.getResponse(criteriaService.pagingFormat(criteriaPage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Criteria> criteriaList = criteriaService.findAllCriteria();
        return ResponseHandler.getResponse(criteriaList, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public Object findCriteriaById(@PathVariable("id") String id) {
        Criteria criteria = criteriaService.findCriteriaById(id);
        return ResponseHandler.getResponse(criteria, HttpStatus.OK);
    }

    @PostMapping
    public Object createCriteria(@Valid @RequestBody CreateCriteriaDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        Criteria criteria = criteriaService.addNewCriteria(dto);

        scanApplicant.callAnalyzeApplicantAPI(dto.getJobPostingId());
        return ResponseHandler.getResponse(criteria, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public Object deleteCriteria(@RequestParam(name = "id") String id) {
        criteriaService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateCriteria(@Valid @RequestBody UpdateCriteriaDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        criteriaService.updateCriteria(dto, dto.getCriteriaId());
        scanApplicant.callAnalyzeApplicantAPI(dto.getJobPostingId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    //    @GetMapping("/search-paging/{paramSearch}")
//    public Object search(@PathVariable String paramSearch, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Criteria> criteriaPage = criteriaService.search(paramSearch, pageable);
//        return ResponseHandler.getResponse(criteriaService.pagingFormat(criteriaPage), HttpStatus.OK);
//    }
//    @GetMapping("/search/{paramSearch}")
//    public Object findCriteriaByNameOrId(@PathVariable String paramSearch) {
//        List<Criteria> employeeList = criteriaService.findCriteriaByNameOrId(paramSearch);
//        if (employeeList.isEmpty()) {
//            return ResponseHandler.getErrors("Not found ", HttpStatus.NOT_FOUND);
//        }
//        return ResponseHandler.getResponse(employeeList, HttpStatus.OK);
//    }
    @GetMapping("/get-by-jobPosting-id/{id}")
    public Object getByJobPostingId(@PathVariable String id) {
        List<Criteria> criteriaList = criteriaService.getByJobPostingId(id);
        return ResponseHandler.getResponse(criteriaList, HttpStatus.OK);
    }

}
