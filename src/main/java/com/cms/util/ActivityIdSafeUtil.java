package com.cms.util;

/**
 * Created by wangliyong on 2019/2/17.
 */
public class ActivityIdSafeUtil {
    public static Integer ActivityIdToSafe(Integer id){
        int aId=0;
        for(int i=1;i<=id;i++){
            if((Integer.parseInt(Integer.toOctalString(i))*i)==id){
                aId=i;
                break;
            }
        }
        return aId;
    }
}
