package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam param){

        return  null;
    }

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit/do"})
    public String add(Model model, HttpServletRequest request, BannerInput bannerInput){
        return null;
    }

    @GetMapping(value = {"admin/banner/add.do","/admin/banner/edit.to"})
    public String addSubmit(Model model, HttpServletRequest request, MultipartFile file){

        return null;
    }

    @GetMapping("/admin/banner/delete.do")
    public String del(BannerInput parameter){
        boolean result = bannerService.del(parameter.getIdList());
        return null;
    }
}
