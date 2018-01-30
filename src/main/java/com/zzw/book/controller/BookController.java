package com.zzw.book.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.base.controller.BaseController;
import com.zzw.base.entity.UserEntity;
import com.zzw.base.model.Message;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.DictionaryUtils;
import com.zzw.base.utils.FileUtil;
import com.zzw.base.utils.SysParamUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    /**
     * 用户业务服务类
     */
    @Autowired
    private UserService userService;

    /*
    * 图书借阅列表界面
    * */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(ModelMap model,Long currentIds){
        model.addAttribute("currentIds", currentIds);
        model.addAttribute("bookType",   DictionaryUtils.getDictionaryByKey("bookType"));
        return "book/bookManage/BorrowList";
    }

    /*
    * 添加图书界面
    * */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(ModelMap model){
        model.addAttribute("bookType",   DictionaryUtils.getDictionaryByKey("bookType"));
        return "/book/bookManage/add";
    }


    /*
    * 图书管理列表界面
    * */
    @RequestMapping(value = "/bookList",method = RequestMethod.GET)
    public String bookList(ModelMap model){
        model.addAttribute("bookType",   DictionaryUtils.getDictionaryByKey("bookType"));
        return "/book/bookManage/bookList";
    }

    /*
    * 分页查询图书信息
    * */
    @RequestMapping(value = "/getPage",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getPage(BookEntity bookEntity, final PageQuery pageQuery){
        Map<String, Object> result = new HashMap<>();
        PageInfo<BookEntity> page = bookService.findPage(bookEntity,pageQuery);
        List<BookEntity> list = page.getList();
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }

    @RequestMapping(value = "/edit" ,method = RequestMethod.GET)
    public String edit(Long id,ModelMap model){
        model.addAttribute("bookType",   DictionaryUtils.getDictionaryByKey("bookType"));
        model.addAttribute("bookEntity", bookService.getById(id));
        model.put("fileUrlPre", SysParamUtils.getString("filesUrlpre"));
        return "/book/bookManage/edit";
    }
     /*
     * 添加图书
     * */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveBook(BookEntity bookEntity, MultipartFile filePath,
                           RedirectAttributes redirectAttributes){
        try {
            if(filePath !=null && filePath.getSize() >0){
                /** 获取文件的后缀* */
                String suffix = filePath.getOriginalFilename().substring(
                        filePath.getOriginalFilename().lastIndexOf("."));
                /** 使用UUID生成文件名称* */
                String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
                FileUtil.uploadFile("/books", filePath, logImageName);
                bookEntity.setBookImg("books/" + logImageName);
            }
            UserEntity userEntity = userService.getCurrentUserEntity();
            if(bookEntity.getId() !=null){
                bookEntity.setUpdateUser(userEntity);
                bookService.updateSelective(bookEntity);
            }else {
                bookEntity.setCreateUser(userEntity);
                bookService.insert(bookEntity);
            }
            this.addFlashMessage(redirectAttributes, Message.success("保存成功"));
        }catch (Exception e){
            this.addFlashMessage(redirectAttributes, Message.success("保存失败"));
        }
        return "redirect:bookList.do";
    }

}
