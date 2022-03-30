package project.apicapstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.service.TimesheetService;

@RestController
@RequestMapping(value = "/v1/api/timesheet")
public class TimesheetController {
    private TimesheetService timesheetService;
    public TimesheetController(TimesheetService timesheetService){
        this.timesheetService=timesheetService;
    }
}
