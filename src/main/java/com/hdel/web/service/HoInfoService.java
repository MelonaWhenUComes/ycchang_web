package com.hdel.web.service;

import com.hdel.web.hoInfo.HoInfo;
import com.hdel.web.hoInfo.HoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HoInfoService {
    @Autowired
    private HoInfoRepository hoInfoRepository;

    public List<HoInfo> findAll() {
        List<HoInfo> hoInfos = new ArrayList<>();
        hoInfoRepository.findAll().forEach(e -> hoInfos.add(e));

        return hoInfos;
    }
}
