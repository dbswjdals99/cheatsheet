package com.ark.cheatsheet.service;

import com.ark.cheatsheet.mapper.MainMapper;
import com.ark.cheatsheet.vo.main.CheatSheetVO;
import com.ark.cheatsheet.vo.main.MainVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    @Autowired
    private final MainMapper mapper;

    public ArrayList<MainVO> getMainList(int category, String order){
        return mapper.getMainList(category, order);
    }

    @Override
    public CheatSheetVO getDetailsContent(String idx, String gate) {
        return mapper.getDetailsContent(idx, gate);
    }

}
