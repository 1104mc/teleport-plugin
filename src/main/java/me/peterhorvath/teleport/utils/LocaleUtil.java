package me.peterhorvath.teleport.utils;

import java.nio.charset.StandardCharsets;

public class LocaleUtil {
    public static String getTextByLocale(String locale, String inpLocale, String text1, String text2){
        return (locale.equals(inpLocale)) ? text1 : text2;
    }

    public static String getLocaledWorldName(String locale, String worldName, boolean addIn, boolean withPronoun){
        String localedWorld = "";
        if(locale.equals("hu_hu")){
            if(worldName.endsWith("nether")) localedWorld = "alvilág";
            else if(worldName.endsWith("nether") && withPronoun) localedWorld = "az alvilág";
            else if(worldName.endsWith("nether") && addIn) localedWorld = "az alvilágban";
            else if(worldName.endsWith("end")) localedWorld = "vég";
            else if(worldName.endsWith("end") && withPronoun) localedWorld = "a vég";
            else if(withPronoun) localedWorld = "a normál világ";
            else if (addIn) localedWorld = "a normál világban";
            else localedWorld = "normál világ";
        }else{
            if(worldName.endsWith("nether")) localedWorld = "nether";
            else if(worldName.endsWith("end")) localedWorld = "end";
            else localedWorld = "normal world";

            if(withPronoun) localedWorld = "the " + localedWorld;
            if(addIn) localedWorld = "in the " + localedWorld;
        }
        return localedWorld;
    }

    public static String getLocaledWorldNameWithPronoun(String locale, String worldName){
        return getLocaledWorldName(locale, worldName, false, true);
    }

    public static String getLocaledWorldNameWithIn(String locale, String worldName){
        return getLocaledWorldName(locale, worldName, true, false);
    }

    public enum UIMenu{
        MainMenu,
        TeleportToPlayer,
        TeleportToWaypoint
    }

    public static UIMenu getMenuByLocaledName(String menuTitle){
        switch (menuTitle){
            case "Teleport menü":
            case "Teleport menu":
                return UIMenu.MainMenu;
            case "Teleportálás játékoshoz":
            case "Teleport to player":
                return UIMenu.TeleportToPlayer;
            case "Teleportálás":
            case "Teleport to destination":
                return UIMenu.TeleportToWaypoint;
            default:
                return null;
        }
    }

    public static String getLocaledMenuTitle(String locale, UIMenu menu){
        switch (menu){
            case MainMenu:
                return getTextByLocale("hu_hu", locale, "Teleport menü", "Teleport menu");
            case TeleportToPlayer:
                return getTextByLocale("hu_hu", locale, "Teleportálás játékoshoz", "Teleport to player");
            case TeleportToWaypoint:
                return getTextByLocale("hu_hu", locale, "Teleportálás célállomáshoz", "Teleport to destination");
            default:
                return "Error! We can't find menu";
        }
    }

    public static String getLocaledTeleportMsg(String locale, String playerName){
        if(locale.equals("hu_hu")) return "Teleportálás " + playerName + " játékoshoz!";
        return "Teleport to " + playerName + " player!";
    }
}
