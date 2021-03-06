package com.cheng.controller.content;

import com.cheng.bean.Ad;
import com.cheng.constant.PageCodeEnum;
import com.cheng.dto.AdDto;
import com.cheng.service.impl.AdServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import javax.xml.ws.soap.Addressing;
import java.util.List;

/**
 * 广告模块控制层
 * Created by cheng on 2017/7/22.
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdServiceImpl adService;

    /**
     * 广告管理页初始化（点击广告管理菜单进入的第一个页面）
     */
    @RequestMapping
    public String initList(Model model) {
        PageInfo<Ad> pageInfo = adService.searchByPage(new AdDto());
        model.addAttribute("pageInfo", pageInfo);
        return "/content/adList";
    }

    /**
     * 查询
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, AdDto adDto) {
        PageInfo<Ad> pageInfo = adService.searchByPage(adDto);
        model.addAttribute("pageInfo", pageInfo);
        return "/content/adList";
    }

    /**
     * 新增页初始化
     */
    @RequestMapping(value = "/addinit", method = RequestMethod.GET)
    public String initModify() {
        return "/content/adAdd";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(AdDto adDto, Model model) {
        if (adService.add(adDto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("id") Long id, Model model) {
        System.out.println(id);
        if (adService.remove(id)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
        }
        return "forward:/ad";
    }
}