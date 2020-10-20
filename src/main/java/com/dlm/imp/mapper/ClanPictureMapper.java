package com.dlm.imp.mapper;

import com.dlm.imp.pojo.ClanPicture;
import com.dlm.imp.pojo.ClanPictureExample;
import java.util.List;

public interface ClanPictureMapper {
    int countByExample(ClanPictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClanPicture record);

    int insertSelective(ClanPicture record);

    List<ClanPicture> selectByExample(ClanPictureExample example);

    ClanPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClanPicture record);

    int updateByPrimaryKey(ClanPicture record);
}