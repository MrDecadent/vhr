package com.dcd.vhr.service;

import com.dcd.vhr.mapper.PositionMapper;
import com.dcd.vhr.model.Position;
import com.dcd.vhr.model.RespBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PositionService {

    @Resource
    PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreatedate(new Date());
        return positionMapper.insertSelective(position);
    }

    public Integer updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePosition(Integer id) {
        return positionMapper.deletePositionByPrimaryKey(id);
    }

    public int deletePositionsByIds(Integer[] ids) {
        return positionMapper.deletePositionsByIds(ids);
    }
}
