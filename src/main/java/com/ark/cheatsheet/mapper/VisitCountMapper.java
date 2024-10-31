package com.ark.cheatsheet.mapper;

import com.ark.cheatsheet.vo.session.ViewCountVO;
import com.ark.cheatsheet.vo.session.VisitCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VisitCountMapper {

    void insertVisitCount(VisitCountVO visitCountVO);
    void updateVisitCount(VisitCountVO visitCountVO);
    VisitCountVO getVisitCount();
    ViewCountVO getViewCount(@Param("idx") String idx);
    void updateViewCount(ViewCountVO viewCountVO);

}
