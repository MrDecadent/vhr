package com.dcd.vhr.service;

import com.dcd.vhr.mapper.NationMapper;
import com.dcd.vhr.model.Nation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NationService {
    @Resource
    NationMapper nationMapper;

    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
