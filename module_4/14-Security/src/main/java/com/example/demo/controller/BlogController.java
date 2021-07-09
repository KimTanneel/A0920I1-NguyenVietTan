package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.service.ECommerceService;
import com.example.demo.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@Controller
public class BlogController {
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private ECommerceService eCommerceService;
    @RolesAllowed("ROLE_MEMBER")
    @GetMapping(value = "/blog")
    public String viewBlog(Model model, @RequestParam(required = false)String key_search, @RequestParam(required = false)String ecomId, @PageableDefault(value = 5) Pageable pageable) {

        model.addAttribute("ecommerces", eCommerceService.findAllECommerce());
        if(key_search==null ){
            System.out.println("key search null");
            key_search="";
        }
        model.addAttribute("key_search",key_search);
        // Neu co id
        if(ecomId!=null && ecomId!=""){
            model.addAttribute("ecomId",ecomId);
            model.addAttribute("blogs",blogService.findBlogsByEcommerceIdAndAuthor(Integer.parseInt(ecomId),key_search,pageable));
            return "blog/list";
        }
        // Neu khong id
        else {
            model.addAttribute("ecomId","");

        }
        model.addAttribute("blogs",blogService.findByAuthorContaining(key_search,pageable));
//        model.addAttribute("dateRelease",new Date());

        return "blog/list";


//        if(!key_search.isPresent()){
//            if(!ecomId.isPresent()){
//                model.addAttribute("blogs",blogService.findAllBLog(pageable));
//                model.addAttribute("ecomId",ecomId.get());
//                System.out.println("Nhay vao Ecom" +ecomId);
//                return "/blog/list";
//            }
//            else{
//                Page<Blog> blogs = blogService.findBlogByEcommerceId(ecomId.get(),pageable);
//                model.addAttribute("blogs",blogs);
//                return "/blog/list";
//            }
//        }else {
//            if(!ecomId.isPresent()){
//                System.out.println("Nhay vao key search");
//                model.addAttribute("key_search", key_search.get());
//                model.addAttribute("blogs",blogService.findByAuthorContaining(key_search.get(),pageable));
//                return "/blog/list";
//            }
//            else{
//                model.addAttribute("key_search",key_search.get());
//                model.addAttribute("ecomId",ecomId.get());
//                model.addAttribute("blogs",blogService.findBlogsByEcommerceIdAndAuthor(ecomId.get(),key_search.get(),pageable));
//            }
//
//        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value="/blog/create")
    public String viewCreate(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("ecommerces",eCommerceService.findAllECommerce());
        return "/blog/create";
    }
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(value = "/blog/save")
    public  String saveBlog(@Validated @ModelAttribute("blog") Blog blog, BindingResult bindingResult, Model model){
        new Blog().validate(blog,bindingResult);
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("blog",blog);
            model.addAttribute("ecommerces",eCommerceService.findAllECommerce());
//            model.addAttribute()
            return "/blog/create";
        }
        else{
            blog.setTimeRelease( new java.sql.Date(new Date().getTime()));
            blogService.saveBlog(blog);
            return "redirect:/blog";
        }

    }
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value = "/blog/edit/{id}")
    public String viewEdit(@PathVariable int id, Model model){
        model.addAttribute("blog",blogService.findBlogById(id));
        return "/blog/edit";

    }
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(value = "/blog/edit")
    public String saveEdit(Blog blog){
        blogService.saveBlog(blog);
        return "redirect:/blog";
    }
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value = "/blog/delete/{id}")
    public String saveDelete(@PathVariable int id){
        blogService.deleteBlogById(id);
        return "redirect:/blog";
    }
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value = "/blog/detail/{id}")
    public String showDetailBlog(Model model, @PathVariable int id){
        model.addAttribute("blog",blogService.findBlogById(id));
        return "/blog/detail";
    }
//    @RolesAllowed({"ROLE_MEMBER","ROLE_ADMIN"})
    @RequestMapping(value = "/blog/sort" )
    public String showsort (Model model, @PageableDefault(value = 5) Pageable pageable){
        model.addAttribute("blogs",blogService.findBlogsOrderByTimeRelease(pageable));
        return "/blog/list";
    }
//    @GetMapping (value = "/blog/eco")
//    public String listBlogFromEcom(Model model, @PageableDefault(value = 5) Pageable pageable){
//
//        model.addAttribute("ecommerces",eCommerceService.findAllECommerce());
//        return "/blog/list";
//    }

}
