package com.ark.cheatsheet.service;

import com.ark.cheatsheet.vo.main.CheatSheetVO;
import com.ark.cheatsheet.vo.main.MainVO;

import java.util.List;

public interface MainService {

    public List<MainVO> getMainList(int category, int gubun);

    public CheatSheetVO getDetailsContent(String idx, String gate);

}
