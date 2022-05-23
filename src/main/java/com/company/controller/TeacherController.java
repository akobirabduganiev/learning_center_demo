package com.company.controller;

import com.company.dto.ChangeTeacherSalaryDTO;
import com.company.dto.TeacherDTO;
import com.company.dto.UserDetailDTO;
import com.company.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/teacher/")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    /**
     * TEACHER PERMISSION
     **/

    @PutMapping("/update-detail")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> updateDetail(@RequestBody UserDetailDTO dto,
                                          Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(teacherService.updateTeacherDetail(dto, userDetails.getUsername()));
    }

    /**
     * ADMIN PERMISSION
     **/

    @GetMapping("/get-teacher/{phone}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherDTO> getTeacherByPhone(@PathVariable("phone") String phone) {
        return ResponseEntity.ok(teacherService.getTeacherByPhone(phone));
    }

    @PutMapping("/update-salary")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSalary(@RequestBody ChangeTeacherSalaryDTO dto) {
        return ResponseEntity.ok(teacherService.updateSalary(dto));
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(teacherService.paginationList(page, size));
    }

    @DeleteMapping(path = "delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        return ResponseEntity.ok(teacherService.delete(id));
    }
}
