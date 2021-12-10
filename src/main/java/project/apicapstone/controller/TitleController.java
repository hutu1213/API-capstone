package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.title.CreateTitleDto;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Title;
import project.apicapstone.service.TitleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/title")
public class TitleController {
    private TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Title> titles = titleService.findAll();
        return ResponseHandler.getResponse(titles, HttpStatus.OK);
    }

    @GetMapping
    public Object findAllTitle(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Title> titlePage = titleService.findAllTitle(pageable);
        return ResponseHandler.getResponse(titleService.pagingFormat(titlePage), HttpStatus.OK);
    }

    @PostMapping()
    public Object createTitle(@Valid @RequestBody CreateTitleDto dto,
                              BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Title createTitle = titleService.createTitle(dto);

        return ResponseHandler.getResponse(createTitle, HttpStatus.CREATED);
    }
}
