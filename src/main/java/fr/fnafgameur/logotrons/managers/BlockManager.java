package fr.fnafgameur.logotrons.managers;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;

public class BlockManager {

    private final HashMap<Location, Block> blockPlaced = new HashMap<>();

    public void addBlockPlaced(Block block, Location location){
        blockPlaced.put(location, block);
    }
    public void removeBlockPlaced(Block block){
        blockPlaced.remove(block);
    }
    public HashMap<Location, Block> getBlockPlaced(){
        return blockPlaced;
    }
}
