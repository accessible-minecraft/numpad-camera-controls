package net.shoaibkhan.ncc;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.shoaibkhan.ncc.config.Config;

public class BridgingAngle {
    private final MinecraftClient client;

    public BridgingAngle(){
        this.client = MinecraftClient.getInstance();
        main();
    }

    private void main(){
        assert client.player != null;
        int xx = 0, yy = 0, zz = 0;
        Direction dir = client.player.getHorizontalFacing();
        Vec3d pos = client.player.getPos();

        if (dir == Direction.NORTH) {
            zz = -2;
        }
        else if (dir == Direction.SOUTH) {
            zz = 2;
        }
        else if (dir == Direction.EAST) {
            xx = 2;
        }
        else if (dir == Direction.WEST) {
            xx = -2;
        }

        yy = -9;

        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(Config.get(Config.getNarratorkey())) NarratorManager.INSTANCE.narrate("Bridging Angle");
    }
}
