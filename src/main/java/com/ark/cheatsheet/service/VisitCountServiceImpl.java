package com.ark.cheatsheet.service;

import com.ark.cheatsheet.mapper.VisitCountMapper;
import com.ark.cheatsheet.vo.session.VisitCountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@RequiredArgsConstructor
public class VisitCountServiceImpl implements VisitCountService{

    @Autowired
    private final VisitCountMapper visitCountMapper;

    public void incrementCount() {
        VisitCountVO visitCount = visitCountMapper.getVisitCount();
        if (visitCount == null) {
            visitCount = new VisitCountVO();
            visitCount.setCount(1L);
            visitCountMapper.insertVisitCount(visitCount);
        } else {
            visitCount.setCount(visitCount.getCount() + 1);
            visitCountMapper.updateVisitCount(visitCount);
        }
    }

    public Long getCount() {
        VisitCountVO visitCount = visitCountMapper.getVisitCount();
        return visitCount == null ? 0 : visitCount.getCount();
    }

}
