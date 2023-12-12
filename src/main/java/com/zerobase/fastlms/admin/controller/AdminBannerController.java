package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam param){

        param.init();
        List<BannerDto> banners = bannerService.list(param);

        long totalCnt = 0;
        if(banners != null && !banners.isEmpty()){
            totalCnt = banners.get(0).getTotalCount();
        }

        String queryString = param.getQueryString();
        String pagerHtml = getPaperHtml(totalCnt, param.getPageSize(), param.getPageIndex(),
                queryString);

        model.addAttribute("list", banners);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pager",pagerHtml);

        return "admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit/do"})
    public String add(Model model, HttpServletRequest request, BannerInput bannerInput){
        boolean editMode = request.getRequestURI().contains("/edit.do");
        BannerDto bannerDto = new BannerDto();

        if(editMode){
            long id = bannerInput.getId();
            BannerDto existsBanner = bannerService.getById(id);
            if(existsBanner == null){
                model.addAttribute("message", "배너정보가 존재하지 않습니다.");
                return "common/error";
            }
            bannerDto = existsBanner;
        }
        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", bannerDto);

        return "admin/banner/add";
    }

    @GetMapping(value = {"admin/banner/add.do","/admin/banner/edit.to"})
    public String addSubmit(Model model, HttpServletRequest request, MultipartFile file, BannerInput bannerInput){

        String[] filePath = FileUtil.getFilePath(file);
        bannerInput.setFileName(filePath[0]);
        bannerInput.setUrlFileName(filePath[1]);

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = bannerInput.getId();
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "배너 정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = bannerService.set(bannerInput);

        } else {
            boolean result = bannerService.add(bannerInput);
        }
        return "redirect:/admin/banner/list.do";
    }

    @GetMapping("/admin/banner/delete.do")
    public String del(BannerInput parameter){
        boolean result = bannerService.del(parameter.getIdList());
        return "redirect:/admin/banner/list.do";
    }
}
