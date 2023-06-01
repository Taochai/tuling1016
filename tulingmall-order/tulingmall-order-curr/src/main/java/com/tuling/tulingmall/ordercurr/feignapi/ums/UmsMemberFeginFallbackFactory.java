package com.tuling.tulingmall.ordercurr.feignapi.ums;

import com.tuling.tulingmall.common.api.CommonResult;
import com.tuling.tulingmall.ordercurr.model.UmsMemberReceiveAddress;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UmsMemberFeginFallbackFactory implements FallbackFactory<UmsMemberFeignApi> {
    
    @Override
    public UmsMemberFeignApi create(Throwable throwable) {
        
        return new UmsMemberFeignApi() {
            @Override
            public CommonResult<UmsMemberReceiveAddress> getItem(Long id) {
                //TODO 业务降级
                UmsMemberReceiveAddress defaultAddress = new UmsMemberReceiveAddress();
                defaultAddress.setName("默认地址");
                defaultAddress.setId(-1L);
                defaultAddress.setDefaultStatus(0);
                defaultAddress.setPostCode("-1");
                defaultAddress.setProvince("默认省份");
                defaultAddress.setCity("默认city");
                defaultAddress.setRegion("默认region");
                defaultAddress.setDetailAddress("默认详情地址");
                defaultAddress.setMemberId(-1L);
                defaultAddress.setPhoneNumber("199xxxxxx");
                return CommonResult.success(defaultAddress);
            }
        };
    }
    
    
}