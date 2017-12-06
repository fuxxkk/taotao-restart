package com.taotao.search.controller;

import com.taotao.common.vo.DatagridResult;
import com.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(value = "q",required = false)String keyword,@RequestParam(value = "page",defaultValue = "1")Integer page){
        ModelAndView mv = new ModelAndView("search");
        try {
            if(StringUtils.isNoneEmpty(keyword)){
                keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
            }
            DatagridResult search = searchService.search(keyword, page);
            mv.addObject("query",keyword);
            mv.addObject("itemList",search.getRows());
            mv.addObject("totalPages",(search.getTotal()+20-1)/20);
            mv.addObject("page",page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

}
