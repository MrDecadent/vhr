package com.dcd.vhr.mapper;

import com.dcd.vhr.model.Position;
import com.dcd.vhr.model.RespBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPositions();

    Integer deletePositionByPrimaryKey(Integer id);

    Integer deletePositionsByIds(@Param("ids") Integer[] ids);
}