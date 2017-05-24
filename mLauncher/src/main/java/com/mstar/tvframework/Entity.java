
package com.mstar.tvframework;

import android.graphics.Bitmap;
import android.util.Log;

public class Entity {
    public static final String TAG = "ENTITY";

    private String name;

    private String sceneName;

    private int subEntityId;

    private int techId;

    private int passId;

    private int pixelFormat;

    private int texUnit;

    public Entity() {

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the passId
     */
    public int getPassId() {
        return passId;
    }

    /**
     * @param passId the passId to set
     */
    public void setPassId(int passId) {
        this.passId = passId;
    }

    /**
     * @return the pixelFormat
     */
    public int getPixelFormat() {
        return pixelFormat;
    }

    /**
     * @param pixelFormat the pixelFormat to set
     */
    public void setPixelFormat(int pixelFormat) {
        this.pixelFormat = pixelFormat;
    }

    /**
     * @return the sceneName
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * @param sceneName the sceneName to set
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    /**
     * @return the subEntityId
     */
    public int getSubEntityId() {
        return subEntityId;
    }

    /**
     * @param subEntityId the subEntityId to set
     */
    public void setSubEntityId(int subEntityId) {
        this.subEntityId = subEntityId;
    }

    /**
     * @return the techId
     */
    public int getTechId() {
        return techId;
    }

    /**
     * @param techId the techId to set
     */
    public void setTechId(int techId) {
        this.techId = techId;
    }

    /**
     * @return the texUnit
     */
    public int getTexUnit() {
        return texUnit;
    }

    /**
     * @param texUnit the texUnit to set
     */
    public void setTexUnit(int texUnit) {
        this.texUnit = texUnit;
    }

    public void update(Bitmap bitmap) {
        MGameEngine gameEngine = MGameEngine.getInstance();

        if (bitmap == null) {
            String luaScript = "OgreKit.getActiveScene():getObject(\"" + name
                    + "\"):setbWidget(false)\n";
            MGameEngine.getInstance().runLuaScript(luaScript.toString());
            Log.i(TAG, "-------------->set widget false<---------" + name);
        } else {
            String luaScript = "OgreKit.getActiveScene():getObject(\"" + name
                    + "\"):setbWidget(true)\n";
            MGameEngine.getInstance().runLuaScript(luaScript.toString());
            Log.i(TAG, "-------------->set widget true<---------" + name);
        }
        gameEngine.setImage(bitmap, null, sceneName, name, subEntityId, techId, passId, texUnit);

    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Entity) {
            return ((Entity) other).getName().equals(getName());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sbContent = new StringBuffer("************************\n");
        sbContent.append("Entity.name=" + name);
        sbContent.append(",Entity.sceneName=" + sceneName);
        sbContent.append(",Entity.subEntityId=" + subEntityId);
        sbContent.append(",Entity.techId=" + techId);
        sbContent.append(",Entity.passId=" + passId);
        sbContent.append(",Entity.texUnit=" + texUnit);
        sbContent.append(",Entity.pixelFormat=" + pixelFormat);
        sbContent.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
        return sbContent.toString();
    }
}
