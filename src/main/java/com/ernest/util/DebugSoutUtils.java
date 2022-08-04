package com.ernest.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ernest
 * @date 2022年04月15
 */
public class DebugSoutUtils {

    /**
     * 在所有的 System.out.println 前均输出当前时间与用户id
     *
     * @return 当前时间+用户id
     */
    public static String soutDebugInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return "id: " +
                session.getAttribute("id").toString() +
                " act: " + session.getAttribute("act") +
                " name: " + session.getAttribute("name") +
                " type: " + session.getAttribute("type") +
                " limit: " + session.getAttribute("limit") +
                "\t";
    }

    /**
     * 在所有的 System.out.println 前均输出当前时间与用户id
     *
     * @return 当前时间+用户id
     */
    public static String soutOnlyTime() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return "[ " + sdf.format(date) + " ]  ";
    }
}
