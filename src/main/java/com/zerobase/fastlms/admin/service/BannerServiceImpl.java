package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
                dto.set
            }
        }
        return null;
    }

    @Override
    public boolean add(BannerInput parameter) {
        return false;
    }

    @Override
    public BannerDto getById(long id) {
        return null;
    }

    @Override
    public boolean set(BannerInput parameter) {
        return false;
    }

    @Override
    public boolean del(String idList) {
        return false;
    }

    @Override
    public List<BannerDto> showList(BannerParam parameter) {
        return null;
    }
}
