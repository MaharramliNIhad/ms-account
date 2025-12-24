package org.company.msaccount.feign;

import org.company.msaccount.model.CardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-card", url = "http://localhost:8080")
public interface CardClient {
    @GetMapping("/v1/cards/{id}")
    CardResponse getCard(@PathVariable Long id);
}
