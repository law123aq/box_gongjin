package com.fongmi.android.tv.utils;

import com.github.catvod.net.OkHttp;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class Filter {

    public static class FilterCache {
        private static List<String> filterList;

        public static List<String> getFilterList() {
            if (filterList == null) {
                try {
                    System.out.println("获取过滤数据: 缓存失效 ");
                    String url = "https://atomgit.com/lintech/tms/raw/master/fiter.json";
                    String resStr = OkHttp.string(url);
                    if (resStr.isEmpty()) {
                        resStr = "{'data':['公众号','神秘的哥哥们','：',':','|','｜','防失联','关注','【','】']}";
                        System.out.println("获取过滤数据: 失败 - " + resStr);
                    }
                    else{
                        System.out.println("获取过滤数据: 成功");
                    }
                    Gson gson = new Gson();
                    Res res = gson.fromJson(resStr, Res.class);
                    filterList = res.data;
                    System.out.println("获取过滤数据: 保存 - "+String.join(", ", filterList));
                } catch (Exception e) {
                    // 处理其他异常
                    e.printStackTrace();
                }
            }
            System.out.println("获取过滤数据: 缓存 - "+String.join(", ", filterList));
            return filterList;
        }



        public static void clearCache() {
            filterList = null;
        }
    }

    public static class Res {
        private List<String> data;

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }
    }

    public static String filterString(String input) {
        try {
            System.out.println("过滤数据: input - "+input);
//            List<String> filterList = FilterCache.getFilterList();
//            if (filterList.isEmpty()) {
//                filterList = Arrays.asList("公众号", "神秘的哥哥们");
//            }
            List<String> filterList = Arrays.asList("┃", "肥猫", "公众号", "神秘的哥哥们", "：","|","｜","防失联","关注","【","】");
            for (String filter : filterList) {
                System.out.println("过滤数据: 循环 - "+filter);
                input = input.replace(filter, "");
            }
            System.out.println("过滤数据: output - "+input);
            return input;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("过滤数据: error - "+input);
            return input;
        }
    }
}