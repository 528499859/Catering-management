package com.meituan.catering.management.shop.api.http.controller;

import com.meituan.catering.management.common.exception.BizException;
import com.meituan.catering.management.common.helper.StatusHelper;
import com.meituan.catering.management.shop.api.http.model.dto.ShopDetailHttpDTO;
import com.meituan.catering.management.shop.api.http.model.request.CloseShopHttpRequest;
import com.meituan.catering.management.shop.api.http.model.request.CreateShopHttpRequest;
import com.meituan.catering.management.shop.api.http.model.request.OpenShopHttpRequest;
import com.meituan.catering.management.shop.api.http.model.request.SearchShopHttpRequest;
import com.meituan.catering.management.shop.api.http.model.request.UpdateShopHttpRequest;
import com.meituan.catering.management.shop.api.http.model.response.ShopDetailHttpResponse;
import com.meituan.catering.management.shop.api.http.model.response.ShopPageHttpResponse;
import com.meituan.catering.management.shop.biz.model.request.SaveShopBizRequest;
import com.meituan.catering.management.shop.biz.model.ShopBO;
import com.meituan.catering.management.shop.biz.model.converter.SaveShopBizRequestConverter;
import com.meituan.catering.management.shop.biz.model.converter.ShopDetailHttpDTOConverter;
import com.meituan.catering.management.shop.biz.service.ShopBizService;
import com.meituan.catering.management.shop.biz.validator.ShopBizServiceValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * ????????????Http API
 *
 * @author dulinfeng
 */
@Api(tags = "????????????")
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopBizService shopBizService;

    @Resource
    private ShopBizServiceValidator shopBizServiceValidator;

    @ApiOperation("???????????????????????????????????????")
    @PostMapping("/search")
    public ShopPageHttpResponse searchForPage(
            @ApiParam("??????ID") @RequestHeader Long tenantId,
            @ApiParam("??????ID") @RequestHeader Long userId,
            @ApiParam("????????????") @Valid @RequestBody SearchShopHttpRequest request) {
        return null;
    }

    @ApiOperation("????????????????????????")
    @GetMapping("/{businessNo}")
    public ShopDetailHttpResponse findByBusinessNo(
            @ApiParam("??????ID") @RequestHeader Long tenantId,
            @ApiParam("??????ID") @RequestHeader Long userId,
            @ApiParam("???????????????") @PathVariable String businessNo) {

        ShopBO shopBO= shopBizService.findByBusinessNo(tenantId,userId,businessNo);
        ShopDetailHttpDTO shopDetailHttpDTO = ShopDetailHttpDTOConverter.toShopDetailHttpResponse(shopBO);
        ShopDetailHttpResponse response=new ShopDetailHttpResponse();
        response.setStatus(StatusHelper.success());
        response.setData(shopDetailHttpDTO);
        return response;
    }

    @ApiOperation("???????????????")
    @PostMapping
    public ShopDetailHttpResponse create(
            @ApiParam("??????ID") @RequestHeader Long tenantId,
            @ApiParam("??????ID") @RequestHeader Long userId,
            @ApiParam("????????????") @Valid @RequestBody CreateShopHttpRequest request) {
        ShopDetailHttpResponse response=new ShopDetailHttpResponse();
        try {
            shopBizServiceValidator.createValid(tenantId,userId,request);
            SaveShopBizRequest saveShopBizRequest= SaveShopBizRequestConverter.toSaveShopBizRequest(request);
            ShopBO shopBO= shopBizService.create(tenantId,userId,saveShopBizRequest);
            response.setStatus(StatusHelper.success());
            response.setData(ShopDetailHttpDTOConverter.toShopDetailHttpResponse(shopBO));
        } catch (BizException e) {
            response.setStatus(StatusHelper.failure(e.getErrorCode()));
        }

        return response;
    }

    @ApiOperation("???????????????????????????")
    @PutMapping("/{businessNo}")
    public ShopDetailHttpResponse update(
            @ApiParam("??????ID") @RequestHeader Long tenantId,
            @ApiParam("??????ID") @RequestHeader Long userId,
            @ApiParam("???????????????") @PathVariable String businessNo,
            @ApiParam("????????????") @Valid @RequestBody UpdateShopHttpRequest request) {
        return null;
    }

    @ApiOperation("??????????????????????????????")
    @PostMapping("/{businessNo}/open")
    public ShopDetailHttpResponse open(
            @ApiParam("??????ID") @RequestHeader Long tenantId,
            @ApiParam("??????ID") @RequestHeader Long userId,
            @ApiParam("???????????????") @PathVariable String businessNo,
            @ApiParam("????????????") @Valid @RequestBody OpenShopHttpRequest request) {
        return null;
    }

    @ApiOperation("??????????????????????????????")
    @PostMapping("/{businessNo}/close")
    public ShopDetailHttpResponse close(
            @ApiParam("??????ID") @RequestHeader Long tenantId,
            @ApiParam("??????ID") @RequestHeader Long userId,
            @PathVariable String businessNo,
            @ApiParam("????????????") @Valid @RequestBody CloseShopHttpRequest request) {
        return null;
    }
}
