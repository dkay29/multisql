package com.dkay229.multisql.rest.server.controller;


import com.dkay229.multisql.rest.server.model.TableListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class MultiSqlServerController {
    @GetMapping("/ping")
    public String ping() {
        log.info("/ping");
        return "Pong!";
    }
    @GetMapping("/listTables")
    public TableListResponse listTables() {
        log.info("/listTables");
        return new TableListResponse(new String[]{"tab1", "tab2"});
    }
    @PostMapping(value="/executeSql")
    public TableListResponse executeSql(@RequestBody String sqlText) {
        log.info("/executeSql: "+sqlText);
        return new TableListResponse(new String[]{"tab1", "tab2"});
    }
}
