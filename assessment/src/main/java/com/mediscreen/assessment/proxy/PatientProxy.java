package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "patient", url = "${mediscreen.patientUrl}")
public interface PatientProxy {
    @GetMapping("/api/patient")
    PatientDto patientById(@RequestParam Long id);
}