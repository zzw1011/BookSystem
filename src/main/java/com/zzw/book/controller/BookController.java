package com.zzw.book.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.base.controller.BaseController;
import com.zzw.base.model.PageQuery;
import com.zzw.book.entity.BookEntity;
import com.zzw.book.service.BookService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建者：zzw
 * @创建时间 2018/1/25 0025
 * @描述 图书管理controller
 */
@Controller("bookController")
@RequestMapping(value = "/admin/book")
public class BookController extends BaseController {

    private static final Log LOG = LogFactory.getLog(BookController.class);

    /*
    * 图书管理业务类
    * */
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(ModelMap model,Long currentIds){
        model.addAttribute("currentIds", currentIds);
        return "/book/bookManage/list";
    }


    @RequestMapping(value = "/getPage",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getPage(BookEntity bookEntity, final PageQuery pageQuery){
        Map<String, Object> result = new HashMap<>();
        PageInfo<BookEntity> page = bookService.findPage(bookEntity,pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }

}
