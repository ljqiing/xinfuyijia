
package com.android.mslauncher;

public class MstarItemInfo implements Cloneable {
    private int id = 0;

    // scene name
    private String scene;

    // scene name
    private String agent;

    // module name
    private String object;

    // name of application
    private String title;

    // package name of application
    private String packageName;

    // class name of application
    private String className;

    // 0 is system app and 1 is data app
    private int level;

    // page num 1~5
    private int page;

    // icon idex at page 0~13
    private int iconindex;

    public void setId(int Id) {
        id = Id;
    }

    public int getId() {
        return id;
    }

    public void setScene(String Scene) {
        scene = Scene;
    }

    public String getScene() {
        return scene;
    }

    public void setAgent(String Agent) {
        agent = Agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setObject(String Object) {
        object = Object;
    }

    public String getObject() {
        return object;
    }

    public void setTitle(String Title) {
        title = Title;
    }

    public String getTitle() {
        return title;
    }

    public void setPackageName(String PackageName) {
        packageName = PackageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setClassName(String ClassName) {
        className = ClassName;
    }

    public String getClassName() {
        return className;
    }

    public void setLevel(int Level) {
        level = Level;
    }

    public int getLevel() {
        return level;
    }

    public void setPage(int Page) {
        page = Page;
    }

    public int getPage() {
        return page;
    }

    public void setIconIndex(int Index) {
        iconindex = Index;
    }

    public int getIconIndex() {
        return iconindex;
    }

    public Object clone() {
        MstarItemInfo o = null;
        try {
            o = (MstarItemInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
