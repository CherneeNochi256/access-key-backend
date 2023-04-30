package com.example.accesskeybackend.template.service;

import com.example.accesskeybackend.template.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URL;

public class CheckSupportService {
    public ResponseEntity<SuccessResponse> checkIPv6SupportBySiteUrl(String siteUrl) {

        if (!siteUrl.startsWith("https://")) {
            siteUrl = "https://" + siteUrl;
        }

        boolean valid = false;
        try {
            URL url = new URL(siteUrl);
            String host = url.getHost();
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress address : addresses) {
                if (address instanceof Inet6Address) {
                    valid = true;
                    break;
                }
            }
        } catch (IOException ignored) {
        }
        return ResponseEntity.ok(new SuccessResponse(valid));
    }
}
