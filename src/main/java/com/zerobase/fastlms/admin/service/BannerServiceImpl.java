package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerMapper bannerMapper;
    private final BannerRepository bannerRepository;

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        long totalCnt = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(BannerDto dto : list){
                dto.setTotalCount(totalCnt);
                dto.setSeq(totalCnt - parameter.getPageStart() - i);
            }
        }
        return list;
    }

    @Override
    public boolean add(BannerInput parameter) {
        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .bannerUrl(parameter.getBannerUrl())
                .openCase(parameter.getOpenCase())
                .showYn(parameter.isShowYn())
                .regDt(parameter.getRegDt())
                .fileName(parameter.getFileName())
                .urlFileName(parameter.getUrlFileName())
                .build();
        this.bannerRepository.save(banner);

        return true;
    }

    @Override
    public BannerDto getById(long id) {
        return this.bannerRepository.findById(id)
                .map(BannerDto::toBanner).orElse(null);
    }

    @Override
    public boolean set(BannerInput parameter) {
        Optional<Banner> optionalBanner = this.bannerRepository.findById(parameter.getId());
        if(!optionalBanner.isPresent()){
            return false;

        }

        Banner banner = optionalBanner.get();
        banner.setBannerName(parameter.getBannerName());
        banner.setBannerUrl(parameter.getBannerUrl());
        banner.setOpenCase(parameter.getOpenCase());
        banner.setSortNum(parameter.getSortNum());
        banner.setShowYn(parameter.isShowYn());
        banner.setFileName(parameter.getFileName());
        banner.setUrlFileName(parameter.getUrlFileName());

        this.bannerRepository.save(banner);
        return true;
    }

    @Override
    public boolean del(String idList) {
        if(idList != null && !idList.isEmpty()){
            String[] ids = idList.split(",");
            for(String s : ids){
                long id = 0L;
                try {
                    id = Long.parseLong(s);
                }catch (Exception e){
                }
                if(id > 0){
                    this.bannerRepository.deleteById(id);
                }
            }
        }
        return true;
    }

    @Override
    public List<BannerDto> showList(BannerParam parameter) {
        return bannerMapper.selectShowList(parameter);
    }
}
