package com.company.controller;

import com.company.dto.ChangePaymentTypeDetailDTO;
import com.company.dto.PaymentTypeDTO;
import com.company.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment-type")
@RequiredArgsConstructor
@Slf4j
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;

    /**
     * ROLE_ADMIN PERMISSION
     **/

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaymentTypeDTO> create(@RequestBody PaymentTypeDTO dto) {
        return ResponseEntity.ok(paymentTypeService.create(dto));
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(paymentTypeService.paginationList(page, size));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaymentTypeDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(paymentTypeService.getById(id));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody ChangePaymentTypeDetailDTO dto) {
        return ResponseEntity.ok(paymentTypeService.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(paymentTypeService.delete(id));
    }
}