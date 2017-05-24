package mstar.tvsetting.factory.ui.designmenu;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class IniUtils{
    public  SharedPreferences sp;
    public DesignMenuActivity context;
    public IniUtils(DesignMenuActivity mActivity){
        context=mActivity;
    }
    public  void setInverted(boolean inverted){
        if (sp == null) {
            sp=context.getPreferences(0);
        }
        Editor edit = sp.edit();
        edit.putBoolean("isVerted", inverted);
        edit.commit();
    }
    public  boolean getInverted(){
        if (sp == null) {
            sp=context.getPreferences(0);
        }
        return sp.getBoolean("isVerted", false);

    }
    public  void setLogo(boolean logo){
        if (sp == null) {
            sp=context.getPreferences(0);
        }
        Editor edit = sp.edit();
        edit.putBoolean("isLogo", logo);
        edit.commit();
    }
    public  boolean getLogo(){
        if (sp == null) {
            sp=context.getPreferences(0);
        }
        return sp.getBoolean("isLogo", false);

    }

    //ljq add
    public void setMusic(boolean music){

        if (sp == null){
            sp = context.getPreferences(0);
        }
        Editor edit = sp.edit();
        edit.putBoolean("isMusic", music);
        edit.commit();

    }

    public boolean getMusic(){

        if (sp == null) {
            sp=context.getPreferences(0);
        }
        return sp.getBoolean("",false);
    }
    //ljq end


    public  void setLvds86(boolean inverted){
        if (sp == null) {
            sp=context.getPreferences(0);
        }
        Editor edit = sp.edit();
        edit.putBoolean("lvds86", inverted);
        edit.commit();
    }
    public  boolean getLvds86(){
        if (sp == null) {
            sp=context.getPreferences(0);
        }
        return sp.getBoolean("lvds86", false);

    }
    public  void setLvdsAB(boolean inverted){
        if (sp == null) {
            sp=context.getPreferences(0);
        }
        Editor edit = sp.edit();
        edit.putBoolean("lvdsab", inverted);
        edit.commit();
    }
    public  boolean getLvdsAB(){
        if (sp == null) {
            sp=context.getPreferences(0);
        }
        return sp.getBoolean("lvdsab", false);

    }
}