package com.company.controller;

import com.company.dto.ChangeTimeTableDetailDTO;
import com.company.dto.TimeTableDTO;
import com.company.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/time-table")
public class TimeTableController {
    private final TimeTableService timeTableService;

    /**
     * ROLE_ADMIN PERMISSION
     **/

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TimeTableDTO> create(@RequestBody TimeTableDTO dto) {
        return ResponseEntity.ok(timeTableService.create(dto));
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(timeTableService.paginationList(page, size));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TimeTableDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(timeTableService.getById(id));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody ChangeTimeTableDetailDTO dto) {
        return ResponseEntity.ok(timeTableService.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(timeTableService.delete(id));
    }
}
