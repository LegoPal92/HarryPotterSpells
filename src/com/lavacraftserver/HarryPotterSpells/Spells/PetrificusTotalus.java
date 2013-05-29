package com.lavacraftserver.HarryPotterSpells.Spells;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import com.lavacraftserver.HarryPotterSpells.HPS;
import com.lavacraftserver.HarryPotterSpells.Spells.Spell.spell;
import com.lavacraftserver.HarryPotterSpells.Utils.Targeter;

@spell(name = "Petrificus Totalus", description = "Stuns the target player", range = 50, goThroughWalls = false)
public class PetrificusTotalus extends Spell implements Listener {
	public static List<String> players = new ArrayList<>();

	@Override
	public void cast(Player p) {
		if (Targeter
				.getTarget(p, this.getRange(), this.canBeCastThroughWalls()) instanceof Player) {
			PetrificusTotalus.players.add(((Player) Targeter.getTarget(p,
					this.getRange(), this.canBeCastThroughWalls())).getName());
			Player target = (Player) Targeter.getTarget(p, this.getRange(),
					this.canBeCastThroughWalls());
			Location loc = new Location(target.getWorld(), target.getLocation()
					.getBlockX(), target.getLocation().getBlockY() + 1, target
					.getLocation().getBlockZ());
			target.getWorld().createExplosion(loc, 0F);
		} else {
			HPS.PM.warn(p, "This may only be used on a player or a mob.");
		}
	}

	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e) {
		if (PetrificusTotalus.players.contains(e.getPlayer().getName()))
			
			e.setTo(e.getFrom());
		
		Bukkit.getServer().getScheduler()
		.scheduleSyncDelayedTask((Plugin) HPS.Plugin.getServer(), new Runnable()
		{
			public void run(){
			e.setTo(e.getTo());
		}
			}, 200);
	}
}
