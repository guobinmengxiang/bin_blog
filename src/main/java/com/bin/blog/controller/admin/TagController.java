package com.bin.blog.controller.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bin.blog.service.TagService;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.Result;
import com.bin.blog.util.ResultGenerator;

@Controller
@RequestMapping("/admin")
public class TagController {
	@Resource
	private TagService tagservice;
	 @GetMapping("/tags")
	public String Tag(HttpServletRequest request){
		 request.setAttribute("path", "tags");
		return "admin/tag";
	}
	 @GetMapping("/tags/list")
	    @ResponseBody
	 public Result list(@RequestParam Map<String,Object> params){
		 if(StringUtils.isEmpty(params.get("page"))|| StringUtils.isEmpty(params.get("limit"))){
			 return ResultGenerator.genFailResult("参数异常！");
			 
		 }
		 PageQueryUtil pageUtil = new PageQueryUtil(params);
		 return ResultGenerator.genSuccessResult(tagservice.getBlogTagPage(pageUtil));
		 
	 }
	  @PostMapping("/tags/save")
	    @ResponseBody
	 public Result saveTag(@RequestParam("tagName") String name){
		 System.out.println("name"+name);
		 if(StringUtils.isEmpty(name)){
			 return ResultGenerator.genFailResult("新增标签不能为空");
		 }
		 if(tagservice.saveTag(name)){
			 System.out.println("boolean值"+tagservice.saveTag(name));
			 return ResultGenerator.genSuccessResult(); 
		 }else{
		
			 return ResultGenerator.genFailResult("标签重复");
		 }
		
		 
		 
	 }
	  @PostMapping("/tags/delete")
	    @ResponseBody
	    public Result delete(@RequestBody Integer[] ids) {
	        if (ids.length < 1) {
	            return ResultGenerator.genFailResult("参数异常！");
	        }
	        if (tagservice.deleteBatch(ids)) {
	            return ResultGenerator.genSuccessResult();
	        } else {
	            return ResultGenerator.genFailResult("有关联数据请勿强行删除");
	        }
	    }
}

