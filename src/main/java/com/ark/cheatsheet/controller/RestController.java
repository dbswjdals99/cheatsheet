package com.ark.cheatsheet.controller;

import com.ark.cheatsheet.service.MainService;
import com.ark.cheatsheet.vo.main.CheatSheetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private MainService mainService;

    @GetMapping("detail-api")
    public ArrayList<CheatSheetVO> detailapi(@RequestParam("idx") String idx) {
        ArrayList<CheatSheetVO> list = new  ArrayList<CheatSheetVO>();
        list = mainService.getDetailsComment(idx);
        return list;
    }
}
