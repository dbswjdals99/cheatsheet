package com.ark.cheatsheet.controller;

import com.ark.cheatsheet.service.MainService;
import com.ark.cheatsheet.service.VisitCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private VisitCountService visitCountService;

    @Autowired
    private MainService mainService;

    @RequestMapping("/")
    public String index(){

        return "redirect:main?gubun=1";
    }

    @RequestMapping(value = "main")
    public ModelAndView main(
            @RequestParam(value = "category", defaultValue = "0") int category,
            @RequestParam(value = "gubun", defaultValue = "0") int gubun,
            HttpSession session){
        ModelAndView model = new ModelAndView();
        model.setViewName("index.html");
        model.addObject("list",mainService.getMainList(category, gubun));

        LocalDate lastVisit = (LocalDate) session.getAttribute("lastVisit");
        LocalDate today = LocalDate.now();

        if (lastVisit == null || !lastVisit.equals(today)) {
            // 사용자가 오늘 처음 방문했을 경우 카운트 증가
            visitCountService.incrementCount();
            session.setAttribute("lastVisit", today);
            System.out.println("*-- 조회수 1 증가 --*");
        }

        model.addObject("visit", visitCountService.getCount());

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

}
