package com.hl.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hl.pojo.TbBrand;
import com.hl.sellergoods.service.BrandService;
import com.hl.vo.PageResult;
import com.hl.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 2018/11/22
 *
 */
@RequestMapping("/brand")
@RestController
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 根据品牌名称、首字母模糊分页查询品牌数据返回分页对象
     * @param page 页号
     * @param rows 页大小
     * @param brand 查询条件对象
     * @return 分页对象
     */
    @PostMapping("/search")
    public PageResult search(@RequestBody TbBrand brand,
           @RequestParam(value = "page",defaultValue = "1") Integer page
           ,@RequestParam(value = "rows",defaultValue = "10") Integer rows){
        return brandService.search(brand,page,rows);
    }

    /**
     * 删除品牌数据
     * */
    @GetMapping("/delete")
    public Result delete(Long[] ids){
        try{
            brandService.deleteByIds(ids);
            return Result.ok("删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("删除失败");
    }


    /**
     * 新增品牌数据
     * */
    @PostMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try{
            brandService.update(brand);
            return Result.ok("修改品牌成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("修改品牌失败");
    }

    @GetMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    /**
     * 新增品牌数据
     * */
    @PostMapping("/add")
    public Result add(@RequestBody TbBrand brand){
        try{
            brandService.add(brand);
            return Result.ok("新增品牌成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("新增品牌失败");
    }


    /**
     * **需求**：在浏览器中访问
     * http://localhost:9100/brand/findAll.do
     * 输出所有数据库中对应的品牌列表json格式字符串
     * */
    @GetMapping("/findAll")
    public List<TbBrand> findAll(){

        // return brandService.queryAll();
        return brandService.findAll();
    }

    /**
     * 测试通用分页插件
     * */
    @GetMapping("/testPage")
    public List<TbBrand> testPage(Integer page,Integer rows){

        // return brandService.testPage(page,size);
       return (List<TbBrand>) brandService.findPage(page,rows).getRows();
    }

    /**
     * 分页查询商品列表数据
     * */
    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam(value = "page",
    defaultValue = "1")Integer page,
    @RequestParam(value = "rows",
            defaultValue = "10")Integer rows){
        return brandService.findPage(page,rows);
    }

    /**
     * 查询品牌列表，返回的数据格式符合select2格式
     * */
    @GetMapping("/selectOptionList")
    public List<Map<String,Object>> selectOptionList(){
        return brandService.selectOptionList();
    }
}
