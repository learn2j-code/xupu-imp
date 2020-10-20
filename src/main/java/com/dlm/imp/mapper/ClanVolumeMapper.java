package com.dlm.imp.mapper;

import com.dlm.imp.pojo.ClanVolume;
import com.dlm.imp.pojo.ClanVolumeExample;
import java.util.List;

public interface ClanVolumeMapper {
    int countByExample(ClanVolumeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClanVolume record);

    int insertSelective(ClanVolume record);

    List<ClanVolume> selectByExample(ClanVolumeExample example);

    ClanVolume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClanVolume record);

    int updateByPrimaryKey(ClanVolume record);
}