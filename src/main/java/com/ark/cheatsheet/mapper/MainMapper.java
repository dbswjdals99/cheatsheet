package com.ark.cheatsheet.mapper;

import com.ark.cheatsheet.vo.main.CheatSheetVO;
import com.ark.cheatsheet.vo.main.MainVO;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface MainMapper {

    public ArrayList<MainVO> getMainList(@Param("category") int category, @Param("order") String order);

    public CheatSheetVO getDetailsContent(String idx, String gate);

}
