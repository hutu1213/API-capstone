package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.position.CreatePositionDto;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Position;
import project.apicapstone.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/position")
public class PositionController {
    private PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }
    @GetMapping
    public Object findAllPosition(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Position> positionPage = positionService.findAllPosition(pageable);
        return ResponseHandler.getResponse(positionService.pagingFormat(positionPage), HttpStatus.OK);
    }
    @GetMapping("/get-all")
    public Object findAll() {
        List<Position> positions = positionService.findAll();
        return ResponseHandler.getResponse(positions, HttpStatus.OK);
    }
    @PostMapping("/create-position")
    public Object createPosition(@Valid @RequestBody CreatePositionDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Position createPosition = positionService.createPosition(dto);

        return ResponseHandler.getResponse(createPosition, HttpStatus.CREATED);
    }
}
