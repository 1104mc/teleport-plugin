package me.peterhorvath.teleport.utils;

import me.peterhorvath.teleport.Teleport;
import me.peterhorvath.teleport.model.TeleportRequest;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class TeleportUtil {
    private static final ArrayList<TeleportRequest> tpRequests = new ArrayList<>();

    public static void init(){
        new BukkitRunnable() {
            @Override
            public void run() {
                tpRequests.removeIf(request -> {
                    if(request.isExpired()){
                        request.expired();
                        return true;
                    }
                    return false;
                });
            }
        }.runTaskTimer(Teleport.getProvidingPlugin(Teleport.class), 0L, 20L);
    }

    public static void askForTeleport(Player requester, Player target){
        tpRequests.add(new TeleportRequest(requester, target));
    }

    private static TeleportRequest getFirstRequestForPlayer(Player target){
        List<TeleportRequest> requests = tpRequests.stream().filter(request -> request.isThisTarget(target)).toList();
        if(requests.isEmpty()) return null;
        return requests.get(0);
    }

    public static void acceptTeleport(Player target){
        TeleportRequest request = getFirstRequestForPlayer(target);
        if(request == null) return;
        request.accept();
        tpRequests.remove(request);
    }

    public static void rejectTeleport(Player target){
        TeleportRequest request = getFirstRequestForPlayer(target);
        if(request == null) return;
        request.reject();
        tpRequests.remove(request);
    }
}
