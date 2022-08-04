package com.ernest.intercepter;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FilePathPic extends WebMvcConfigurerAdapter {

    private final String ezioPcPath = "D:\\Learning_File\\Code_Program\\java\\TransDemo\\files\\";
    private final String ernestPcPath = "D:\\code_of_JAVA_2021-7\\duan\\TransDemo\\files\\";
    private final String linuxPath = "/usr/local/duan/files/";
    //    private final String windowsPath = "file:///" + linuxPath;
    private final String windowsPath = "file:///" + linuxPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("/Path/**").addResourceLocations(windowsPath);
        super.addResourceHandlers(registry);
    }

}
