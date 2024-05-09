package com.technicjelle.bluemapofflineplayermarkers.impl.folia1_20;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;

public final class Scheduler {


    private static final boolean isFolia = Bukkit.getVersion().contains("Folia");

    public static void run(Runnable runnable) {
        if (isFolia)
            Bukkit.getGlobalRegionScheduler()
                    .execute(BlueMapOfflinePlayerMarkers.getInstance(), runnable);

        else
            Bukkit.getScheduler().runTask(BlueMapOfflinePlayerMarkers.getInstance(), runnable);
    }

    public static Task runLater(Runnable runnable, long delayTicks) {
        if (isFolia)
            return new Task(Bukkit.getGlobalRegionScheduler()
                        .runDelayed(BlueMapOfflinePlayerMarkers.getInstance(), t -> runnable.run(), delayTicks));

        else
            return new Task(Bukkit.getScheduler().runTaskLater(BlueMapOfflinePlayerMarkers.getInstance(), runnable, delayTicks));
    }
    public static Task runLaterng(Location loc,Runnable runnable, long delayTicks) {
        if (isFolia)
            return new Task(Bukkit.getRegionScheduler()
                    .runDelayed(BlueMapOfflinePlayerMarkers.getInstance(), loc, t -> runnable.run(), delayTicks));

        else
            return new Task(Bukkit.getScheduler().runTaskLater(BlueMapOfflinePlayerMarkers.getInstance(), runnable, delayTicks));
    }
    public static Task runLaterngnl(World world, int x, int z,Runnable runnable, long delayTicks) {
        if (isFolia)
            return new Task(Bukkit.getRegionScheduler()
                    .runDelayed(BlueMapOfflinePlayerMarkers.getInstance(), world, x, z, t -> runnable.run(), delayTicks));

        else
            return new Task(Bukkit.getScheduler().runTaskLater(BlueMapOfflinePlayerMarkers.getInstance(), runnable, delayTicks));
    }

    public static Task runTimer(Runnable runnable, long delayTicks, long periodTicks) {
        if (isFolia)
            return new Task(Bukkit.getGlobalRegionScheduler()
                    .runAtFixedRate(BlueMapOfflinePlayerMarkers.getInstance(), t -> runnable.run(), delayTicks < 1 ? 1 : delayTicks, periodTicks));

        else
            return new Task(Bukkit.getScheduler().runTaskTimer(BlueMapOfflinePlayerMarkers.getInstance(), runnable, delayTicks, periodTicks));
    }
    public static Task runTimerng(Location loc,Runnable runnable, long delayTicks, long periodTicks) {
        if (isFolia)
            return new Task(Bukkit.getRegionScheduler()
                    .runAtFixedRate(BlueMapOfflinePlayerMarkers.getInstance(), loc, t -> runnable.run(), delayTicks < 1 ? 1 : delayTicks, periodTicks));

        else
            return new Task(Bukkit.getScheduler().runTaskTimer(BlueMapOfflinePlayerMarkers.getInstance(), runnable, delayTicks, periodTicks));
    }
    public static Task runTimerngnl(World world, int x, int z, Runnable runnable, long delayTicks, long periodTicks) {
        if (isFolia)
            return new Task(Bukkit.getRegionScheduler()
                    .runAtFixedRate(BlueMapOfflinePlayerMarkers.getInstance(), world, x, z, t -> runnable.run(), delayTicks < 1 ? 1 : delayTicks, periodTicks));

        else
            return new Task(Bukkit.getScheduler().runTaskTimer(BlueMapOfflinePlayerMarkers.getInstance(), runnable, delayTicks, periodTicks));
    }




    public static boolean isFolia() {
        return isFolia;
    }

    public static class Task {

        private Object foliaTask;
        private BukkitTask bukkitTask;

        Task(Object foliaTask) {
            this.foliaTask = foliaTask;
        }

        Task(BukkitTask bukkitTask) {
            this.bukkitTask = bukkitTask;
        }

        public void cancel() {
            if (foliaTask != null)
                ((ScheduledTask) foliaTask).cancel();
            else
                bukkitTask.cancel();
        }
    }
}