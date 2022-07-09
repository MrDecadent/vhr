package com.dcd.vhr.service;

import com.dcd.vhr.mapper.PoliticsstatusMapper;
import com.dcd.vhr.model.Politicsstatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class politicsStatusService {

    @Resource
    PoliticsstatusMapper mapper;


    public List<Politicsstatus> getAllPolitic() {
        return mapper.getAllPolitic();
    }
}
