package com.rainbowbaby.controller;

import com.rainbowbaby.my_util.GetMusic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页控制器
 */
@Controller
public class IndexController {
    @Autowired
    private GetMusic getMusic;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/post/{musicName}")
    @ResponseBody
    public String index(@PathVariable("musicName")String musicName){
        //先查询缓存中是否存在
        String st = stringRedisTemplate.opsForValue().get(musicName);
        if(st!=null){
            return "playMusic("+"{\"a\":\""+st+"\"})";
        }
        st = getMusic.GetMusicByName(musicName);
        //字符串分割
        String[] str = st.split("=");
        //存入缓存
        stringRedisTemplate.opsForValue().set(musicName,str[1]);
        st = "playMusic("+"{\"a\":\""+str[1]+"\"})";
        return st;
    }


    @GetMapping(value="/index")
    public String ToIndex(){
        return "index";
    }
}
