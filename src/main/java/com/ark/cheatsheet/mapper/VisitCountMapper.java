package com.ark.cheatsheet.mapper;

import com.ark.cheatsheet.vo.session.VisitCountVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitCountMapper {

    void insertVisitCount(VisitCountVO visitCountVO);
    void updateVisitCount(VisitCountVO visitCountVO);
    VisitCountVO getVisitCount();

}
