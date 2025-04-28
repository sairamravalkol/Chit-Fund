package com.jbtech.chit_fund.controller;

import com.jbtech.chit_fund.model.ChitGroup;
import com.jbtech.chit_fund.service.ChitGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chit-groups")
public class ChitGroupController {

    private final ChitGroupService chitGroupService;

    public ChitGroupController(ChitGroupService chitGroupService) {
        this.chitGroupService = chitGroupService;
    }

    @PostMapping
    public ResponseEntity<ChitGroup> createChitGroup(@RequestBody ChitGroup chitGroup) {
        ChitGroup createdChitGroup = chitGroupService.createChitGroup(chitGroup);
        return ResponseEntity.ok(createdChitGroup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChitGroup> getChitGroupById(@PathVariable Long id) {
        return chitGroupService.getChitGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ChitGroup>> getAllChitGroups() {
        List<ChitGroup> chitGroups = chitGroupService.getAllChitGroups();
        return ResponseEntity.ok(chitGroups);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChitGroup> updateChitGroup(@PathVariable Long id, @RequestBody ChitGroup chitGroup) {
        try {
            ChitGroup updatedChitGroup = chitGroupService.updateChitGroup(id, chitGroup);
            return ResponseEntity.ok(updatedChitGroup);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChitGroup(@PathVariable Long id) {
        chitGroupService.deleteChitGroup(id);
        return ResponseEntity.noContent().build();
    }
}