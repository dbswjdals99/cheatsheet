package com.ark.cheatsheet.controller;

import com.ark.cheatsheet.service.MainService;
import com.ark.cheatsheet.service.VisitCountService;
import com.ark.cheatsheet.utils.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private VisitCountService visitCountService;

    @Autowired
    private MainService mainService;

    @Autowired
    private SessionListener sessionListener;

    @RequestMapping("/")
    public String index(){

        return "redirect:main?gubun=1";
    }

    @RequestMapping(value = "main")
    public ModelAndView main(
            @RequestParam(value = "category", defaultValue = "0") int category,
            @RequestParam(value = "gubun", defaultValue = "0") int gubun,
            HttpSession session){

        // ModelAndView 객체 선언
        ModelAndView model = new ModelAndView();

        // index.html 연동
        model.setViewName("index.html");

        // 메인페이지 게시물 정보 가져오기
        model.addObject("list",mainService.getMainList(category, gubun));

        // 사이트 방문자 누적조회수 증가 로직
        LocalDate lastVisit = (LocalDate) session.getAttribute("lastVisit");
        LocalDate today = LocalDate.now();

        if (lastVisit == null || !lastVisit.equals(today)) {
            // 사용자가 오늘 처음 방문했을 경우 카운트 증가
            visitCountService.incrementCount();
            session.setAttribute("lastVisit", today);
            System.out.println("*-- 조회수 1 증가 --*");
        }

        model.addObject("visit", visitCountService.getCount());

        int activeUsers = sessionListener.getActiveSessions();
        model.addObject("count", activeUsers);

        return model;
    }

    @RequestMapping(value = "details")
    public ModelAndView details(@RequestParam("idx") String idx, @RequestParam(value = "gate", required = false, defaultValue = "1") String gate){
        ModelAndView model = new ModelAndView();
        model.setViewName("details.html");
        model.addObject("details",mainService.getDetailsContent(idx,gate));

        return model;
    }

    @RequestMapping(value = "imgView")
    public ModelAndView imgView(){
        ModelAndView model = new ModelAndView();
        model.setViewName("imgView.html");
        return model;
    }

    @RequestMapping(value = "calculator")
    public ModelAndView calculator(){
        ModelAndView model = new ModelAndView();
        model.setViewName("calculator.html");

        return model;
    }

}
