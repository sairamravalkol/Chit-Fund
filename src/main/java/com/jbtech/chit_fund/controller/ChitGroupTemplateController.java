package com.jbtech.chit_fund.controller;


import com.jbtech.chit_fund.model.ChitGroupTemplate;
import com.jbtech.chit_fund.service.ChitGroupTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chit-group-templates")
public class ChitGroupTemplateController {

    @Autowired
    private ChitGroupTemplateService service;

    @PostMapping
    public ResponseEntity<ChitGroupTemplate> create(@RequestBody ChitGroupTemplate chitGroupTemplate) {
        ChitGroupTemplate created = service.create(chitGroupTemplate);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ChitGroupTemplate>> getAll() {
        List<ChitGroupTemplate> templates = service.getAll();
        return new ResponseEntity<>(templates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChitGroupTemplate> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(template -> new ResponseEntity<>(template, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChitGroupTemplate> update(@PathVariable Long id, @RequestBody ChitGroupTemplate chitGroupTemplate) {
        ChitGroupTemplate updated = service.update(id, chitGroupTemplate);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
