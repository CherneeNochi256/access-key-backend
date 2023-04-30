package com.example.accesskeybackend.template.controller;

import com.example.accesskeybackend.template.dto.SuccessResponse;
import com.example.accesskeybackend.template.service.CheckSupportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web")
@AllArgsConstructor
public class CheckSupportController {

    private final CheckSupportService checkSupportService;

    @GetMapping("checkIpv6Support")
    public ResponseEntity<SuccessResponse> checkIpv6Support(@RequestParam String siteUrl) {
        return checkSupportService.checkIPv6SupportBySiteUrl(siteUrl);
    }
}
