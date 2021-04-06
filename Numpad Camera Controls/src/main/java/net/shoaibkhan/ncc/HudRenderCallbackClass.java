package net.shoaibkhan.ncc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.data.client.model.VariantSettings;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class HudRenderCallbackClass {
    private MinecraftClient client;
    public HudRenderCallbackClass(){
        client = MinecraftClient.getInstance();
        HudRenderCallback.EVENT.register((__,___) -> {
            if (client.player != null){
                try{
                    Vec3d pos = client.player.getPos();

                    while (Initial.num_5.wasPressed()) {
                        //Center Axis
                        int xx = 0, yy = 0, zz = 0;
                        String dir = client.player.getHorizontalFacing().toString().toLowerCase().trim();
                        if (dir.contains("north")) zz = -1;
                        else if (dir.contains("south")) zz = 1;
                        else if (dir.contains("east")) xx = 1;
                        else if (dir.contains("west")) xx = -1;
                        else xx = 1;
                        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }

                    while (Initial.num_6.wasPressed()) {
                        int xx = 0, yy = 0, zz = 0;
                        int angle = (int)client.player.getRotationClient().y;
                        while(angle>=360) angle -= 360;
                        while(angle<=-360) angle += 360;
                        if(angle>=-150&&angle<=-120){
                            xx = 1;
                        } else if(angle>=-50&&angle<=-20){
                            zz = 1;
                        } else if(angle>=30&&angle<=60){
                            xx = -1;
                        } else if(angle>=120&&angle<=150){
                            zz = -1;
                        } else {
                            String dir = client.player.getHorizontalFacing().asString();
                            dir = dir.toLowerCase().trim();
                            if(dir.contains("north")){
                                zz = -1;
                                xx = 1;
                            } else if(dir.contains("south")){
                                zz = 1;
                                xx = -1;
                            } else if(dir.contains("east")){
                                zz = 1;
                                xx = 1;
                            } else if(dir.contains("west")){
                                zz = -1;
                                xx = -1;
                            }
                        }
                        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }
                    
                    while (Initial.num_4.wasPressed()) {
                        int xx = 0, yy = 0, zz = 0;
                        int angle = (int)client.player.getRotationClient().y;
                        while(angle>=360) angle -= 360;
                        while(angle<=-360) angle += 360;
                        if(angle>=-150&&angle<=-120){
                            zz = -1;
                        } else if(angle>=-50&&angle<=-20){
                            xx = 1;
                        } else if(angle>=30&&angle<=60){
                            zz = 1;
                        } else if(angle>=120&&angle<=150){
                            xx = -1;
                        } else {
                            String dir = client.player.getHorizontalFacing().asString();
                            dir = dir.toLowerCase().trim();
                            if(dir.contains("north")){
                                zz = -1;
                                xx = -1;
                            } else if(dir.contains("south")){
                                zz = 1;
                                xx = 1;
                            } else if(dir.contains("east")){
                                zz = -1;
                                xx = 1;
                            } else if(dir.contains("west")){
                                zz = 1;
                                xx = -1;
                            }
                        }
                        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }
                    
                    while(Initial.num_2.wasPressed()) {
                        int xx = 0, yy = 0, zz = 0;
                        String dir = client.player.getHorizontalFacing().toString().toLowerCase().trim();
                        if (dir.contains("north")) zz = -1;
                        else if (dir.contains("south")) zz = 1;
                        else if (dir.contains("east")) xx = 1;
                        else if (dir.contains("west")) xx = -1;
                        else xx = 1;
                        int angle = (int)client.player.getRotationClient().x;
                       
                        if(angle<90&&angle>=60) {
                        	xx = 0;
                        	zz = 0;
                        	yy = -2;
                        } else if(angle<60&&angle>=30) {
                        	xx = 0;
                        	zz = 0;
                        	yy = -2;
                        } else if(angle<30&&angle>=0) {
                        	yy = -1;
                        } else if(angle<0&&angle>=-30) {
                        	yy = 0;
                        } else if(angle<-30&&angle>=-60) {
                        	yy = 0;
                        } else if(angle<-60&&angle>=-90) {
                        	yy = 1;
                        }
                        
                        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }
                    
                    while(Initial.num_8.wasPressed()) {
                        int xx = 0, yy = 0, zz = 0;
                        String dir = client.player.getHorizontalFacing().toString().toLowerCase().trim();
                        if (dir.contains("north")) zz = -1;
                        else if (dir.contains("south")) zz = 1;
                        else if (dir.contains("east")) xx = 1;
                        else if (dir.contains("west")) xx = -1;
                        else xx = 1;
                        int angle = (int)client.player.getRotationClient().x;
                        
                        if(angle<90&&angle>=60) {
                        	yy = -1;
                        } else if(angle<60&&angle>=30) {
                        	yy = 0;
                        } else if(angle<30&&angle>=10) {
                        	yy = 0;
                        } else if(angle<10&&angle>=-30) {
                        	yy = 1;
                        } else if(angle<-30&&angle>=-60) {
                        	xx = 0;
                        	zz = 0;
                        	yy = 2;
                        } else if(angle<-60&&angle>=-90) {
                        	xx = 0;
                        	zz = 0;
                        	yy = 2;
                        }
                        
                        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }

                    while (Initial.num_7.wasPressed()) {
                        // Look North
                        Vec3d vec3d = new Vec3d(pos.x+0 , pos.y+0, pos.z-1);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }

                    while (Initial.num_9.wasPressed()) {
                        // Look East
                        Vec3d vec3d = new Vec3d(pos.x+1 , pos.y+0, pos.z+0);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }

                    while (Initial.num_3.wasPressed()) {
                        // Look West
                        Vec3d vec3d = new Vec3d(pos.x-1 , pos.y+0, pos.z+0);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }

                    while (Initial.num_1.wasPressed()) {
                        // Look South
                        Vec3d vec3d = new Vec3d(pos.x+0 , pos.y+0, pos.z+1);
                        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}
