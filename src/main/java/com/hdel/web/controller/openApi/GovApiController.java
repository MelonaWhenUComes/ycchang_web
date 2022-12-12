package com.hdel.web.controller.openApi;

import com.hdel.web.dto.api.NaverNews;
import com.hdel.web.service.api.GovApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openApi")
@CrossOrigin
public class GovApiController {
    @Autowired
    GovApiService govApiService;
    //private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/public/getSelfCheck/{query}")
    public void getSelfCheck() throws Exception {
        govApiService.getSelfCheck();
    }

    @GetMapping(path = "/public/getSelfCheckDupException")
    public void getSelfCheckDupException() throws Exception {
        govApiService.getSelfCheckDupException();
    }

    @GetMapping(path = "/public/getInspectFromGov")
    public void getInspectFromGov() throws Exception {
        govApiService.getInspectFromGov();
    }

    @PostMapping(path = "/public/insertGovElevatorInfoFromGovApi")
    public void insertGovElevatorInfoFromGovApi() throws Exception {
        govApiService.insertGovElevatorInfoFromGovApi();
    }

}
