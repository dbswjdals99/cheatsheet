package com.ark.cheatsheet.service;

import com.ark.cheatsheet.mapper.VisitCountMapper;
import com.ark.cheatsheet.vo.session.ViewCountVO;
import com.ark.cheatsheet.vo.session.VisitCountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    public void detailsCount(@RequestParam("idx") String idx, HttpServletResponse response, HttpServletRequest request) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("detailsView")) {
                    oldCookie = cookie;
                }
            }
        }

        //게시물 조회 수 증가
        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + idx + "]")) {
                ViewCountVO viewCount = visitCountMapper.getViewCount(idx);
                viewCount.setView(viewCount.getView() + 1);
                oldCookie.setValue(oldCookie.getValue() + "_[" + idx + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
                visitCountMapper.updateViewCount(viewCount);
            }
        } else {
            ViewCountVO viewCount = visitCountMapper.getViewCount(idx);
            viewCount.setView(viewCount.getView() + 1);
            Cookie newCookie = new Cookie("detailsView","[" + idx + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
            visitCountMapper.updateViewCount(viewCount);
        }
    }

}
