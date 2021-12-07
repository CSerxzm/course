package com.xzm.course.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzm.course.model.entity.MajorEntity;
import com.xzm.course.model.vo.response.table.MajorItemVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorMapper extends BaseMapper<MajorEntity> {
    Integer count(@Param("departmentName") String departmentName, @Param("name") String name);

    IPage<MajorItemVO> getPage(IPage<MajorItemVO> page, @Param("departmentName")String departmentName,@Param("name") String name);
}
