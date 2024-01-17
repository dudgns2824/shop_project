package com.dudgns.purchase.item.service;

import com.dudgns.purchase.item.dto.RequestItemInfoDto;
import com.dudgns.purchase.item.dto.ResponseItemInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ItemService {
    public ResponseItemInfoDto info(RequestItemInfoDto req) {
        return ResponseItemInfoDto.builder()

                .build();
    }
}
