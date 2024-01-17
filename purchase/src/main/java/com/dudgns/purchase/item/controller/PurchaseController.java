package com.dudgns.purchase.item.controller;

import com.dudgns.purchase.dto.BaseRepsonseDto;
import com.dudgns.purchase.item.dto.RequestItemInfoDto;
import com.dudgns.purchase.item.dto.ResponseItemInfoDto;
import com.dudgns.purchase.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchase/item")
public class PurchaseController {

    private final ItemService itemService;

    @PostMapping("info")
    public ResponseEntity<BaseRepsonseDto> info(RequestItemInfoDto req) {

        ResponseItemInfoDto res = itemService.info(req);

        return ResponseEntity.ok(BaseRepsonseDto.builder()
                .statusCode(200)
                .status("정상")
                .data(res)
                .build());
    }
}
