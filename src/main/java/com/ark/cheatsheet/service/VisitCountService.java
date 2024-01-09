package com.ark.cheatsheet.service;

import com.ark.cheatsheet.vo.session.VisitCountVO;

public interface VisitCountService {

    public void incrementCount();

    public Long getCount();

}
