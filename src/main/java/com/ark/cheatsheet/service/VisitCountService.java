package com.ark.cheatsheet.service;

import com.ark.cheatsheet.vo.session.VisitCountVO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface VisitCountService {

    public void incrementCount();

    public Long getCount();

    public void detailsCount(@RequestParam("idx") String idx, HttpServletResponse response, HttpServletRequest request);

}
