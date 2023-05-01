package com.example.accesskeybackend.template.service;

import com.example.accesskeybackend.template.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URL;

public class CheckSupportService {
    public ResponseEntity<SuccessResponse> checkIPv6SupportBySiteUrl(String siteUrl) {

        String host = siteUrl == null ? "" : siteUrl;

        if (host.startsWith("https://")) {
            host = host.substring("https://".length());
        } else if (host.startsWith("http://")) {
            host = host.substring("http://".length());
        }
        host = host.split("/")[0];

        boolean valid = false;
        try {
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
