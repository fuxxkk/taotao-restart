package com.taotao.item.controller;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;
import com.taotao.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;

    @RequestMapping(value = "/{itemid}",method = RequestMethod.GET)
    public ModelAndView toItemPage(@PathVariable("itemid")Long itemId){

        ModelAndView mv = new ModelAndView("item");

        try {
            Item item = itemService.queryById(itemId);
            ItemDesc itemDesc = itemDescService.queryById(itemId);
            mv.addObject("item", item);
            mv.addObject("itemDesc",itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

}
