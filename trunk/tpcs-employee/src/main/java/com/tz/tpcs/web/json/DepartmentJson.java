package com.tz.tpcs.web.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门 json 封装类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/6 13:48
 */
public class DepartmentJson {

    private String id;
    private String text;
    private List<DepartmentJson> children;
    private Map<String,Object> state;

    /**
     * 空参构造
     */
    public DepartmentJson() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DepartmentJson> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentJson> children) {
        this.children = children;
    }

    /**
     * 根据下属部门信息，初始化state
     * @return
     */
    public Map<String, Object> getState() {
        if(this.children != null
                && this.children.size()>0
                && state == null){
            this.state = new HashMap<>();
            state.put("opened", true);
        }
        return state;
    }

    public void setState(Map<String, Object> state) {
        this.state = state;
    }
}
