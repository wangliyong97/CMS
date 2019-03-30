package com.cms.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangliyong on 2019/2/6.
 */
@RequestMapping(value = "/admin")
@Controller
public class FileBrowseUtil {

    @RequestMapping("/getFileList")
    @ResponseBody
    protected Map<String, Object> CalculateGeoServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MalformedURLException {
        ArrayList<String> fileList=new ArrayList<String>();
        String params="d:\\upload\\background";
        fileList=getFiles(params,fileList);
        Map<String, Object> map=new HashMap<String, Object>();
        if(fileList.size()>0){
            map.put("status", 200);
        }else{
            map.put("status", 0);
        }
        map.put("fileList", fileList);
        return map;
    }

    /**
     * 通过递归得到某一路径下所有的目录及其文件
     * @param filePath 文件路径
     * @return
     */
    public static ArrayList<String> getFiles(String filePath, ArrayList<String> fileList) {
        ArrayList<String> fileListAll =fileList;
        File root = new File(filePath);
        File[] files = root.listFiles();
        String[] arr=new String[10];
        if(files!=null){
            for (File file : files) {
                if (file.isDirectory()) {
                /*
                 * 递归调用
                 */
                    arr=file.getAbsolutePath().split(":");
                    getFiles(arr[1].replace("\\","/"),fileListAll);
                } else {
                    arr=file.getAbsolutePath().split(":");
                    fileList.add(arr[1].replace("\\","/"));
                }
            }
        }
        return fileListAll;
    }
}
