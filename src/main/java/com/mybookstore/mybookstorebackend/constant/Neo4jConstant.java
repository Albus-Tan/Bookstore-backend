package com.mybookstore.mybookstorebackend.constant;


import java.util.HashMap;
import java.util.HashSet;

public class Neo4jConstant {
    public static final HashSet<String> TYPES = new HashSet<String>() {{
        add("教育");
        add("文学");
        add("小说");
        add("图书");
    }};

    public static final HashMap<String, HashSet<String>> RELATIONS = new HashMap<String, HashSet<String>>() {{
        put("文学", new HashSet<String>(){{
            add("儿童文学");
            add("青春文学");
            add("传记文学");
        }});
        put("小说", new HashSet<String>(){{
            add("魔幻小说");
            add("科幻小说");
            add("社会小说");
            add("悬疑/推理小说");
            add("武侠小说");
        }});
        put("教育", new HashSet<String>(){{
            add("编程");
            add("中小学教辅");
        }});
        put("图书", new HashSet<String>(){{
            add("文学");
            add("小说");
            add("教育");
            add("古籍");
            add("世界名著");
        }});
    }};
}
