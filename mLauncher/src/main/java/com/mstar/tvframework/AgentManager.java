
package com.mstar.tvframework;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;

import com.android.mslauncher.MstarUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.Xml;

public class AgentManager {
    // debug flags
    private final static boolean LOG_ALL_FUNCTION_CALL = true;

    private final static String TAG = "AgentManager";

    private final static int NAME_ATTR_IDX = 0;

    private final static int SCENE_ATTR_IDX = 1;

    private final static int SUBENT_ATTR_IDX = 2;

    private final static int TECH_ATTR_IDX = 3;

    private final static int PASS_ATTR_IDX = 4;

    private final static int TEXUNIT_ATTR_IDX = 5;

    private final static int PF_ATTR_IDX = 6;

    private MGameEngine gameEngine = null;

    private Map<String, Agent> agentMap = new Hashtable<String, Agent>();

    private Map<Agent, MHandler> handlerEMap = new Hashtable<Agent, MHandler>();

    private Map<Agent, MHandler> handlerUMap = new Hashtable<Agent, MHandler>();

    private boolean bInitialized = false;

    private MDefaultEventHandler defaultEventHandler = new MDefaultEventHandler();

    private MDefaultUpdateHandler defaultUpdateHandler = new MDefaultUpdateHandler();

    public AgentManager() {

        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "AgentManager.AgentManager");
        }
    }

    public boolean init(MGameEngine g) {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "AgentManager.init");
        }

        if (null == g)
            return false;

        if (!bInitialized) {
            gameEngine = g;
            build();
            bInitialized = true;
        }
        return true;
    }

    public void build() {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "AgentManager.build");
        }
        try {
            parseAgentXml();
        } catch (Exception ex) {
            Log.v(TAG, "Parse xml to getAllAgetn encounter exception!" + ex.getMessage());
        }
    }

    public boolean bind(MHandler h, String agentName) {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "AgentManager.bind");
        }
        Map<Agent, MHandler> handlerMap = null;
        // foreach agentMap agent.name == agentName

        if (agentMap.containsKey(agentName)) {
            Agent a = agentMap.get(agentName);
            if (h.getType() == MHandler.handlerType.HANDLER_EVENT) {
                handlerMap = handlerEMap;
            } else if (h.getType() == MHandler.handlerType.HANDLER_UPDATE) {
                handlerMap = handlerUMap;
            }
            if (handlerMap != null) {
                handlerMap.put(a, h);
                a.bind(h);
                return true;
            }
        }
        return false;
    }

    public boolean unbind(MHandler h, String agentName) {
        if (LOG_ALL_FUNCTION_CALL) {
            Log.v(TAG, "AgentManager.unbind");
        }

        // foreach agentMap agent.name == agentName

        if (agentMap.containsKey(agentName)) {
            Agent a = agentMap.get(agentName);

            if (h.getType() == MHandler.handlerType.HANDLER_EVENT) {
                if (handlerEMap.containsKey(a)) {
                    handlerEMap.put(a, defaultEventHandler);
                    a.bind(defaultEventHandler);
                    return true;
                }
            }
            if (h.getType() == MHandler.handlerType.HANDLER_UPDATE) {
                if (handlerUMap.containsKey(a)) {
                    handlerUMap.put(a, defaultUpdateHandler);
                    a.bind(defaultUpdateHandler);
                    return true;
                }

            }
        }
        return false;
    }

    public boolean handleEvent(MGameEvent e) {

        String agentName = e.getAgentName();
        Log.v(TAG, "!!!!!!!!!!!!!!!!!!!!! AgentManager handleEvent agentName=" + agentName);
        if (agentName != null && agentMap.containsKey(agentName)) {
            Agent a = agentMap.get(agentName);
            if (a.dispatchEvent(e)) {
                return true;
            }
        } else {
            Log.v(TAG, "AgentManager handleEvent can not find agent " + e.getAgentName());
        }
        return false;
    }

    public boolean update(String agentName, String entityName, Bitmap image) {
        if (agentMap.containsKey(agentName)) {
            Agent agent = agentMap.get(agentName);
            agent.update(entityName, image);
        }

        return true;
    }

    public Agent getAgent(String agentName) {
        if (agentMap.containsKey(agentName)) {
            return agentMap.get(agentName);
        }
        return null;
    }

    private void parseAgentXml() throws Exception {
        InputStream inputStream = null;
        try {
            Activity activity = MstarUtil.getInstance().getActivity();
            if (activity != null) {
                if (activity instanceof Mstar3DUIActivity) {
                    Mstar3DUIActivity activity_3DUI = (Mstar3DUIActivity) activity;
                    int lastSepa_pos = activity_3DUI.agentFile.lastIndexOf("/");
                    String agentFileName = activity_3DUI.agentFile.substring(lastSepa_pos + 1);
                    // Log.v(TAG, "parseAgentXml() function agentFileName=" +
                    // agentFileName);
                    inputStream = activity_3DUI.openFileInput(agentFileName);
                    XmlPullParser xmlPull = Xml.newPullParser();
                    xmlPull.setInput(inputStream, "utf-8");
                    Agent agent = null;
                    Entity entity = null;
                    String agentName = null;
                    int eventcode = xmlPull.getEventType();
                    while (eventcode != XmlPullParser.END_DOCUMENT) {
                        switch (eventcode) {
                            case XmlPullParser.START_DOCUMENT: {
                                Log.v(TAG, "parseXml:begin to parse xml....");
                                break;
                            }
                            case XmlPullParser.START_TAG: {
                                String tagName = xmlPull.getName();
                                // Log.v(TAG, "+++++parseAgentXml tagName=" +
                                // tagName);
                                if (Agent.AGENT_TAG.equals(tagName)) {
                                    // parse Agent
                                    agent = new Agent();
                                    agentName = xmlPull.getAttributeValue(0);
                                    agent.name = agentName;
                                    // Log.v(TAG,
                                    // "Parse AGENT Tag create Agent name=" +
                                    // agentName);
                                } else if (Entity.TAG.equals(tagName)) {
                                    // parse Entity
                                    entity = new Entity();
                                    entity.setName(xmlPull.getAttributeValue(NAME_ATTR_IDX));
                                    entity.setSceneName(xmlPull.getAttributeValue(SCENE_ATTR_IDX));
                                    entity.setSubEntityId(Integer.parseInt(xmlPull
                                            .getAttributeValue(SUBENT_ATTR_IDX)));
                                    entity.setTechId(Integer.parseInt(xmlPull
                                            .getAttributeValue(TECH_ATTR_IDX)));
                                    entity.setPassId(Integer.parseInt(xmlPull
                                            .getAttributeValue(PASS_ATTR_IDX)));
                                    entity.setTexUnit(Integer.parseInt(xmlPull
                                            .getAttributeValue(TEXUNIT_ATTR_IDX)));
                                    entity.setPixelFormat(Integer.parseInt(xmlPull
                                            .getAttributeValue(PF_ATTR_IDX)));

                                    // Log.v(TAG,"++++++parse entity element,entity obj is "
                                    // + entity);
                                    if (agent != null) {
                                        // Log.v(TAG,"!!!!!!!!!!!!!!!Agent append entity");
                                        agent.appendChild(entity);
                                        entity = null;
                                    }

                                }
                                break;
                            }
                            case XmlPullParser.END_TAG: {
                                if (Agent.AGENT_TAG.equals(xmlPull.getName())) {
                                    Log.v(TAG, "+++++++++++++++++Parse one Agent Finish!!! name="
                                            + agentName);
                                    agentMap.put(agentName, agent);
                                    // bind defaultEventHandler
                                    bind(defaultEventHandler, agentName);
                                    // bind defaultUpdateHandler
                                    bind(defaultUpdateHandler, agentName);
                                    agent = null;
                                }
                                break;
                            }
                            case XmlPullParser.END_DOCUMENT: {
                                // end parse xml
                                Log.v(TAG, "++++++parse Agent.xml  finish!!");
                                break;
                            }
                        }
                        eventcode = xmlPull.next();
                    }
                }

            }

        } catch (Exception ex) {
            Log.v(TAG, "Parse xml to getAllAgent encounter exception:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
    }

    public String translateAgentName(String agentName) {
        if (agentMap.containsKey(agentName)) {
            return agentName;
        } else { // maybe agentname regard as entity
            for (Object o : agentMap.keySet()) {
                Agent agent = agentMap.get(o);
                if (agent.getEntityByName(agentName) != null)
                    return (String) o;
            }

        }
        return null;
    }

}
