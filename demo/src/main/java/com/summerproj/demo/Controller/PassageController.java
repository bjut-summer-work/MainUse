package com.summerproj.demo.Controller;

import com.summerproj.demo.Entity.Passage;
import com.summerproj.demo.Now;
import com.summerproj.demo.Repository.PassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.text.*;

@RequestMapping(value="/user/write")
public class PassageController {
    @Autowired
    private PassageRepository PassageRepository;

    private String jumpMessage="返回主页";
    private String jumpUrl="/home";

    /**
     * jump页面
     */
    @GetMapping(value = "/jump")
    public String passagejumpP(Model model){
        model.addAttribute("jumpMessage",jumpMessage);
        model.addAttribute("jumpUrl",jumpUrl);
        return "jump";
    }

    /**
     * 文案编制页面
     */
    @GetMapping(value = "/write")
    public String passageAddP1(Model model){
        return "write";
    }
    @Transactional
    @PostMapping(value = "/write")
    public String passageAdd1(@RequestParam("title") String i_title,
                          @RequestParam("article") String i_article){
        Passage passage = new Passage();

        passage.setTitle(i_title);
        passage.setAuthor(Now.getUser().getUsername());

        passage.setDate(new Date());
        passage.setArticle(i_article);
        passage.setType(1);//改！！
        PassageRepository.save(passage);

        jumpMessage = "编制成功！将跳转主界面";
        jumpUrl = "/user_home";
        return "redirect:/jump";
    }


}
