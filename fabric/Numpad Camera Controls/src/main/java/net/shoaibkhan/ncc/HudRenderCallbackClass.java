package net.shoaibkhan.ncc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Vec3d;
import net.shoaibkhan.ncc.mixin.AccessorHandledScreen;

@Environment(EnvType.CLIENT)
public class HudRenderCallbackClass {
	private MinecraftClient client;
	
	public HudRenderCallbackClass(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
        	this.client = client;
            if (client.player != null && !(client.currentScreen instanceof AccessorHandledScreen)){
                try{
                	Vec3d pos = client.player.getPos();
                    boolean isAltPressed = (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), InputUtil.fromTranslationKey("key.keyboard.left.alt").getCode())||InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), InputUtil.fromTranslationKey("key.keyboard.right.alt").getCode())); 
                    
                    while(isAltPressed && ClientInitializer.centreAxis.wasPressed()){
                    	centreAxisReverse(pos);
                    }

                    while (ClientInitializer.centreAxis.wasPressed()&&!isAltPressed) {
                    	centreAxis(pos);
                    }

                    while (ClientInitializer.rotateRight.wasPressed()) {
                    	rotateRight(pos);
                    }
                    
                    while (ClientInitializer.rotateLeft.wasPressed()) {
                    	rotateLeft(pos);
                    }
                    
                    while(ClientInitializer.rotateDownwards.wasPressed()) {
                    	rotateDown(pos);
                    }
                    
                    while(ClientInitializer.rotateUp.wasPressed()) {
                    	rotateUp(pos);
                    }

                    while (ClientInitializer.lookNorth.wasPressed()) {
                    	lookNorth(pos);
                    }

                    while (ClientInitializer.lookSouth.wasPressed()) {
                    	lookSouth(pos);
                    }

                    while (ClientInitializer.lookEast.wasPressed()) {
                    	lookEast(pos);
                    }

                    while (ClientInitializer.lookWest.wasPressed()) {
                    	lookWest(pos);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
	
	private void centreAxisReverse(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        String dir = client.player.getHorizontalFacing().toString().toLowerCase().trim();
        String string = "";
        if (dir.contains("north")) {
        	zz = +1;
        	string = "south";
        }
        else if (dir.contains("south")) {
        	zz = -1;
        	string = "north";
        }
        else if (dir.contains("east")) {
        	xx = -1;
        	string = "west";
        }
        else if (dir.contains("west")) {
        	xx = 01;
        	string = "east";
        }
        else {
        	xx = 1;
        	string = "east";
        }
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void centreAxis(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        String dir = client.player.getHorizontalFacing().toString().toLowerCase().trim();
        String string = "";
        if (dir.contains("north")) {
        	zz = -1;
        	string = "north";
        }
        else if (dir.contains("south")) {
        	zz = 1;
        	string = "south";
        }
        else if (dir.contains("east")) {
        	xx = 1;
        	string = "east";
        }
        else if (dir.contains("west")) {
        	xx = -1;
        	string = "west";
        }
        else {
        	xx = 1;
        	string = "east";
        }
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateRight(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        int angle = (int)client.player.getRotationClient().y;
        String string = "";
        while(angle>=360) angle -= 360;
        while(angle<=-360) angle += 360;
        if(angle>=-150&&angle<=-120){
        	// Look east
            xx = 1;
            string = "east";
        } else if(angle>=-50&&angle<=-20){
        	// Look south
        	zz = 1;
            string = "south";
        } else if(angle>=30&&angle<=60){
        	// Look west
        	xx = -1;
            string = "west";
        } else if(angle>=120&&angle<=150){
        	// Look north
        	zz = -1;
            string = "north";
        } else {
            String dir = client.player.getHorizontalFacing().asString();
            dir = dir.toLowerCase().trim();
            if(dir.contains("north")){
            	// Look north-east
            	zz = -1;
                xx = 1;
                string = "north-east";
            } else if(dir.contains("south")){
            	// Look south-west
            	zz = 1;
                xx = -1;
                string = "south-west";
            } else if(dir.contains("east")){
            	// Look south-east
            	zz = 1;
                xx = 1;
                string = "south-east";
            } else if(dir.contains("west")){
            	// Look north-west
            	zz = -1;
                xx = -1;
                string = "north-west";
            }
        }
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateLeft(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        int angle = (int)client.player.getRotationClient().y;
        String string = ""; 
        while(angle>=360) angle -= 360;
        while(angle<=-360) angle += 360;
        if(angle>=-150&&angle<=-120){
        	// Look north
        	zz = -1;
        	string = "north";
        } else if(angle>=-50&&angle<=-20){
        	// Look east
        	xx = 1;
        	string = "east";
        } else if(angle>=30&&angle<=60){
        	// Look south
        	zz = 1;
        	string = "south";
        } else if(angle>=120&&angle<=150){
        	// Look west
        	xx = -1;
        	string = "west";
        } else {
            String dir = client.player.getHorizontalFacing().asString();
            dir = dir.toLowerCase().trim();
            if(dir.contains("north")){
            	// Look north-west
            	zz = -1;
                xx = -1;
            	string = "north-west";
            } else if(dir.contains("south")){
            	// Look south-east
                zz = 1;
                xx = 1;
            	string = "south-east";
            } else if(dir.contains("east")){
            	// Look north-east
                zz = -1;
                xx = 1;
            	string = "north-east";
            } else if(dir.contains("west")){
            	// Look south-west
                zz = 1;
                xx = -1;
            	string = "south-west";
            }
        }
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateDown(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        int angle1 = (int)client.player.getRotationClient().y;
        String string = ""; 
        while(angle1>=360) angle1 -= 360;
        while(angle1<=-360) angle1 += 360;
        
        if(angle1>=-150&&angle1<=-120){
        	// Looking North East
        	zz = -1;
        	xx = 1;
        } else if(angle1>=-50&&angle1<=-20){
        	// Looking South East
        	zz = 1;
        	xx = 1;
        } else if(angle1>=30&&angle1<=60){
        	// Looking South West
        	zz = 1;
        	xx = -1;
        } else if(angle1>=120&&angle1<=150){
        	// Looking North West
        	zz = -1;
        	xx = -1;
        } else {
            String dir = client.player.getHorizontalFacing().asString();
            dir = dir.toLowerCase().trim();
            if (dir.contains("north")) zz = -1;
            else if (dir.contains("south")) zz = 1;
            else if (dir.contains("east")) xx = 1;
            else if (dir.contains("west")) xx = -1;
            else xx = 1;
        }
        
        
        int angle = (int)client.player.getRotationClient().x;
       
        if(angle<=90&&angle>=60) {
        	string = "down";
        	yy = -30;
        } else if(angle<60&&angle>=30) {
        	string = "down";
        	yy = -30;
        } else if(angle<30&&angle>=0) {
        	yy = -1;
        } else if(angle<0&&angle>=-30) {
        	string = "straight";
        	yy = 0;
        } else if(angle<-30&&angle>=-60) {
        	string = "straight";
        	yy = 0;
        } else if(angle<-60&&angle>=-90) {
        	yy = 1;
        }
        
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(!string.equals("")) client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateUp(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        int angle1 = (int)client.player.getRotationClient().y;
        String string = ""; 
        while(angle1>=360) angle1 -= 360;
        while(angle1<=-360) angle1 += 360;
        
        if(angle1>=-150&&angle1<=-120){
        	// Looking North East
        	zz = -1;
        	xx = 1;
        } else if(angle1>=-50&&angle1<=-20){
        	// Looking South East
        	zz = 1;
        	xx = 1;
        } else if(angle1>=30&&angle1<=60){
        	// Looking South West
        	zz = 1;
        	xx = -1;
        } else if(angle1>=120&&angle1<=150){
        	// Looking North West
        	zz = -1;
        	xx = -1;
        } else {
            String dir = client.player.getHorizontalFacing().asString();
            dir = dir.toLowerCase().trim();
            if (dir.contains("north")) zz = -1;
            else if (dir.contains("south")) zz = 1;
            else if (dir.contains("east")) xx = 1;
            else if (dir.contains("west")) xx = -1;
            else xx = 1;
        }
        int angle = (int)client.player.getRotationClient().x;
        
        if(angle<90&&angle>=60) {
        	yy = -1;
        } else if(angle<60&&angle>=30) {
        	string = "straight";
        	yy = 0;
        } else if(angle<30&&angle>=10) {
        	string = "straight";
        	yy = 0;
        } else if(angle<10&&angle>=-30) {
        	yy = 1;
        } else if(angle<-30&&angle>=-60) {
        	string = "up";
        	yy = 30;
        } else if(angle<-60&&angle>=-90) {
        	string = "up";
        	yy = 30;
        }
        
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(!string.equals("")) client.player.sendMessage(new LiteralText(string), true);
	}
    
    private void lookNorth(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x+0 , pos.y+0, pos.z-1);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        client.player.sendMessage(new LiteralText("north"), true);
    }
    
    private void lookSouth(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x+0 , pos.y+0, pos.z+1);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        client.player.sendMessage(new LiteralText("south"), true);
    }
    
    private void lookWest(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x-1 , pos.y+0, pos.z+0);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        client.player.sendMessage(new LiteralText("west"), true);
    }
    
    private void lookEast(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x+1 , pos.y+0, pos.z+0);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        client.player.sendMessage(new LiteralText("east"), true);
    }
    
}
