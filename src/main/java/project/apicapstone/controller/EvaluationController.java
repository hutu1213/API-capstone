package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.dto.employee.UpdateEmployeeDto;
import project.apicapstone.dto.evaluation.CreateEvaluationDto;
import project.apicapstone.dto.evaluation.UpdateEvaluationDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Evaluation;
import project.apicapstone.service.EvaluationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/evaluation")
public class EvaluationController {
    private EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping
    public Object findAllEvaluation(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Evaluation> evaluationPage = evaluationService.findAll(pageable);
        return ResponseHandler.getResponse(evaluationService.pagingFormat(evaluationPage), HttpStatus.OK);
    }

    @GetMapping("/search-paging/{paramSearch}")
    public Object search(@PathVariable String paramSearch, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Evaluation> evaluationPage = evaluationService.search(paramSearch, pageable);
//        if(paramSearch.isEmpty()){
//            return ResponseHandler.getResponse("Vui lòng nhập", HttpStatus.BAD_REQUEST);
//        }
        return ResponseHandler.getResponse(evaluationService.pagingFormat(evaluationPage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Evaluation> evaluations = evaluationService.findAllEvaluation();
        return ResponseHandler.getResponse(evaluations, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public Object findEvaluationById(@PathVariable("id") String id) {
        Evaluation evaluation = evaluationService.findEvaluationById(id);
        return ResponseHandler.getResponse(evaluation, HttpStatus.OK);
    }

    @PostMapping
    public Object createEvaluation(@Valid @RequestBody CreateEvaluationDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        Evaluation evaluation = evaluationService.addEvaluation(dto);
        return ResponseHandler.getResponse(evaluation, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public Object deleteEvaluation(@RequestParam(name = "id") String id) {
        evaluationService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateEvaluation(@Valid @RequestBody UpdateEvaluationDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        evaluationService.updateEvaluation(dto, dto.getEvaluationId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
