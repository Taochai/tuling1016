package com.tuling.tulingmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuling.tulingmall.dto.PmsProductResult;
import com.tuling.tulingmall.model.PmsProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    /**
     * 获取商品编辑信息
     */
    @Select("SELECT *," +
            "            pc.parent_id cateParentId," +
            "            l.id ladder_id,l.product_id ladder_product_id,l.discount ladder_discount,l.count ladder_count,l.price ladder_price," +
            "            pf.id full_id,pf.product_id full_product_id,pf.full_price full_full_price,pf.reduce_price full_reduce_price," +
            "            m.id member_id,m.product_id member_product_id,m.member_level_id member_member_level_id,m.member_price member_member_price,m.member_level_name member_member_level_name," +
            "            s.id sku_id,s.product_id sku_product_id,s.price sku_price,s.low_stock sku_low_stock,s.pic sku_pic,s.sale sku_sale,s.sku_code sku_sku_code,s.sp1 sku_sp1,s.sp2 sku_sp2,s.sp3 sku_sp3,s.stock sku_stock," +
            "            a.id attribute_id,a.product_id attribute_product_id,a.product_attribute_id attribute_product_attribute_id,a.value attribute_value" +
            "        FROM pms_product p" +
            "        LEFT JOIN pms_product_category pc on pc.id = p.product_category_id" +
            "        LEFT JOIN pms_product_ladder l ON p.id = l.product_id" +
            "        LEFT JOIN pms_product_full_reduction pf ON pf.product_id=p.id" +
            "        LEFT JOIN pms_member_price m ON m.product_id = p.id" +
            "        LEFT JOIN pms_sku_stock s ON s.product_id = p.id" +
            "        LEFT JOIN pms_product_attribute_value a ON a.product_id=p.id" +
            "        WHERE p.id=#{id};")
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}