package com.example.accesskeybackend.template.service;

import com.example.accesskeybackend.template.dto.SuccessResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckSupportServiceTest {

    private CheckSupportService underTest = new CheckSupportService();


    @Test
    public void testCheckIPv6SupportBySiteUrlWithValidSiteUrl() {
        // given
        List<String> siteUrlList = List.of("www.example.com", "https://www.google.com", "www.youtube.com");

        siteUrlList.forEach(url -> {
                    // when
                    ResponseEntity<SuccessResponse> response = underTest.checkIPv6SupportBySiteUrl(url);

                    // then
                    assertNotNull(response);
                    assertEquals(HttpStatus.OK, response.getStatusCode());
                    SuccessResponse body = response.getBody();
                    assertNotNull(body);
                    assertTrue(body.getSuccess());
                }
        );
    }


    @Test
    public void testCheckIPv6SupportBySiteUrlWithInvalidSiteUrl() {
        // given
        String siteUrl = "invalidurl";

        // when
        ResponseEntity<SuccessResponse> response = underTest.checkIPv6SupportBySiteUrl(siteUrl);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SuccessResponse body = response.getBody();
        assertNotNull(body);
        assertFalse(body.getSuccess());
    }

    // this test may fall after some time because these sites can start support IPv6
    @Test
    public void testCheckIPv6SupportBySiteThatIsKnownForNotSupportingIPv6() {
        // given
        List<String> urlList = List.of("https://ok.ru", "www.avito.ru");//sites are taken from https://whynoipv6.com

        urlList.forEach(url -> {
            // when
            ResponseEntity<SuccessResponse> response = underTest.checkIPv6SupportBySiteUrl(url);

            // then
            assertNotNull(response);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            SuccessResponse body = response.getBody();
            assertNotNull(body);
            assertFalse(body.getSuccess());
        });
    }
}