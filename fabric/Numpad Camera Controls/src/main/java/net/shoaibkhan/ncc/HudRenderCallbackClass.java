package net.shoaibkhan.ncc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Vec3d;
import net.shoaibkhan.ncc.config.Config;
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
                    
                    
                    if(isAltPressed) {
                        while(isAltPressed && ClientInitializer.centreAxis.wasPressed()){
                        	centreAxisReverse(pos);
                        }
                        
                        while (ClientInitializer.rotateRight.wasPressed()) {
                        	rotateRightAlt(pos);
                        }
                        
                        while (ClientInitializer.rotateLeft.wasPressed()) {
                        	rotateLeftAlt(pos);
                        }
                        
                        while(ClientInitializer.rotateDownwards.wasPressed()) {
                        	rotateDownAlt(pos);
                        }
                        
                        while(ClientInitializer.rotateUp.wasPressed()) {
                        	rotateUpAlt(pos);
                        }
                    } else {
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

                        while (ClientInitializer.centreAxis.wasPressed()) {
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
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
        
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
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateRight(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        int angle = (int)client.player.getRotationClient().y;
        String string = "";
        while(angle>=360) angle -= 360;
        while(angle<=-360) angle += 360;
        if((angle>=-150&&angle<=-120)||(angle>=210&&angle<=240)){
        	// Look east
            xx = 1;
            string = "east";
        } else if((angle>=-60&&angle<=-30)||(angle>=300&&angle<=330)){
        	// Look south
        	zz = 1;
            string = "south";
        } else if((angle>=30&&angle<=60)||(angle>=-330&&angle<=-300)){
        	// Look west
        	xx = -1;
            string = "west";
        } else if((angle>=120&&angle<=150)||(angle>=-240&&angle<=-210)){
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
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateLeft(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        int angle = (int)client.player.getRotationClient().y;
        String string = ""; 
        while(angle>=360) angle -= 360;
        while(angle<=-360) angle += 360;
        if((angle>=-150&&angle<=-120)||(angle>=210&&angle<=240)){
        	// Look north
        	zz = -1;
        	string = "north";
        } else if((angle>=-60&&angle<=-30)||(angle>=300&&angle<=330)){
        	// Look east
        	xx = 1;
        	string = "east";
        } else if(((angle>=30&&angle<=60)||(angle>=-330&&angle<=-300))||(angle>=-330&&angle<=-300)){
        	// Look south
        	zz = 1;
        	string = "south";
        } else if((angle>=120&&angle<=150)||(angle>=-240&&angle<=-210)){
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
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
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
        if(!string.equals("")&&Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
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
        if(!string.equals("")&&Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
	}

	private void rotateRightAlt(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        int angle = (int)client.player.getRotationClient().y;
        String string = "";
        while(angle>=360) angle -= 360;
        while(angle<=-360) angle += 360;
        
        if(angle>=-170&&angle<=-160){
        	zz = -2;
            xx = 1;
        } else if(angle>=-80&&angle<=-70){
        	zz = 1;
        	xx = 2;
        } else if(angle>=10&&angle<=20){
        	zz = 2;
        	xx = -1;
        } else if(angle>=100&&angle<=110){
        	zz = -1;
        	xx = -2;
        } else if(angle>=-155&&angle<=-145){
        	// Look north-east
        	zz = -1;
            xx = 1;
            string = "north-east";
        } else if(angle>=-65&&angle<=-55){
        	// Look south-east
        	zz = 1;
            xx = 1;
            string = "south-east";
        } else if(angle>=25&&angle<=35){
        	// Look south-west
        	zz = 1;
            xx = -1;
            string = "south-west";
        } else if(angle>=115&&angle<=125){
        	// Look north-west
        	zz = -1;
            xx = -1;
            string = "north-west";
        } else if(angle>=-140&&angle<=-130){
        	// Looking north-east
        	zz = -1;
            xx = 2;
        } else if(angle>=-50&&angle<=-40){
        	// Looking south-east
        	zz = 2;
            xx = 1;
        } else if(angle>=40&&angle<=50){
        	// Looking south-west
        	zz = 1;
            xx = -2;
        } else if((angle>=120&&angle<=150)||(angle>=-240&&angle<=-210)){
        	// Looking north-west
        	zz = -2;
            xx = -1;
        } else if(angle>=-125&&angle<=-115){
        	zz = -1;
            xx = 4;
        } else if(angle>=-35&&angle<=-25){
        	zz = 4;
        	xx = 1;
        } else if(angle>=55&&angle<=65){
        	zz = 1;
        	xx = -4;
        } else if(angle>=145&&angle<=155){
        	zz = -4;
        	xx = -1;
        } else if(angle>=-110&&angle<=-100){
        	// Look east
            xx = 1;
            string = "east";
        } else if(angle>=-20&&angle<=-10){
        	// Look south
        	zz = 1;
            string = "south";
        } else if(angle>=70&&angle<=80){
        	// Look west
        	xx = -1;
            string = "west";
        } else if(angle>=160&&angle<=170){
        	// Look north
        	zz = -1;
            string = "north";
        } else {
            String dir = client.player.getHorizontalFacing().asString();
            dir = dir.toLowerCase().trim();
            if(dir.contains("north")){
            	zz = -4;
                xx = 1;
            } else if(dir.contains("south")){
            	zz = 4;
                xx = -1;
            } else if(dir.contains("east")){
            	zz = 1;
                xx = 4;
            } else if(dir.contains("west")){
            	zz = -1;
                xx = -4;
            }
        }
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateLeftAlt(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        int angle = (int)client.player.getRotationClient().y;
        String string = "";
        while(angle>=360) angle -= 360;
        while(angle<=-360) angle += 360;
        
        if(angle>=-170&&angle<=-160){
        	// Look north
        	zz = -1;
            string = "north";
        } else if(angle>=-80&&angle<=-70){
        	// Look east
            xx = 1;
            string = "east";
        } else if(angle>=10&&angle<=20){
        	// Look south
        	zz = 1;
            string = "south";
        } else if(angle>=100&&angle<=110){
        	// Look west
        	xx = -1;
            string = "west";
        } else if(angle>=-155&&angle<=-145){
        	zz = -4;
            xx = 1;
        } else if(angle>=-65&&angle<=-55){
        	zz = 1;
            xx = 4;
        } else if(angle>=25&&angle<=35){
        	zz = 4;
            xx = -1;
        } else if(angle>=115&&angle<=125){
        	zz = -1;
            xx = -4;
        } else if(angle>=-140&&angle<=-130){
        	// Looking north-east
        	zz = -2;
            xx = 1;
        } else if(angle>=-50&&angle<=-40){
        	// Looking south-east
        	zz = 1;
            xx = 2;
        } else if(angle>=40&&angle<=50){
        	// Looking south-west
        	zz = 2;
            xx = -1;
        } else if((angle>=120&&angle<=150)||(angle>=-240&&angle<=-210)){
        	// Looking north-west
        	zz = -1;
            xx = -2;
        } else if(angle>=-125&&angle<=-115){
        	// Look north-east
        	zz = -1;
            xx = 1;
            string = "north-east";
        } else if(angle>=-35&&angle<=-25){
        	// Look south-east
        	zz = 1;
            xx = 1;
            string = "south-east";
        } else if(angle>=55&&angle<=65){
        	// Look south-west
        	zz = 1;
            xx = -1;
            string = "south-west";
        } else if(angle>=145&&angle<=155){
        	// Look north-west
        	zz = -1;
            xx = -1;
            string = "north-west";
        } else if(angle>=-110&&angle<=-100){
        	zz = -1;
        	xx = 2;
        } else if(angle>=-20&&angle<=-10){
        	zz = 2;
        	xx = 1;
        } else if(angle>=70&&angle<=80){
        	zz = 1;
        	xx = -2;
        } else if(angle>=160&&angle<=170){
        	zz = -2;
        	xx = -1;
        } else {
            String dir = client.player.getHorizontalFacing().asString();
            dir = dir.toLowerCase().trim();
            if(dir.contains("north")){
            	zz = -4;
                xx = -1;
            } else if(dir.contains("south")){
            	zz = 4;
                xx = 1;
            } else if(dir.contains("east")){
            	zz = -1;
                xx = 4;
            } else if(dir.contains("west")){
            	zz = 1;
                xx = -4;
            }
        }
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateDownAlt(Vec3d pos) {
        double xx = 0, yy = 0, zz = 0;
        double angle1 = (int)client.player.getRotationClient().y;
        String string = ""; 
        
        while(angle1>=360) angle1 -= 360;
        while(angle1<=-360) angle1 += 360;
        
        String dir = client.player.getHorizontalFacing().asString();
        dir = dir.toLowerCase().trim();
        
        double angle = (int)client.player.getRotationClient().x;
        
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
            if (dir.contains("north")) zz = -1;
            else if (dir.contains("south")) zz = 1;
            else if (dir.contains("east")) xx = 1;
            else if (dir.contains("west")) xx = -1;
            else xx = 1;
        }
        
        if(angle<=90&&angle>=70) {
        	yy = -30;
        	string = "down";
        } else if(angle<=65&&angle>=55) {
        	yy = -4;
        	if(angle1>=-150&&angle1<=-120) yy = -5;
        	else if(angle1>=-50&&angle1<=-20) yy = -5;
            else if(angle1>=30&&angle1<=60) yy = -5;
            else if(angle1>=120&&angle1<=150) yy = -5;
            else {
                if (dir.contains("north")) yy = -4;
                else if (dir.contains("south")) yy = -4;
                else if (dir.contains("east")) yy = -4;
                else if (dir.contains("west")) yy = -4;
                else yy = -4;
            }
        } else if(angle<=50&&angle>=40) {
        	if(angle1>=-150&&angle1<=-120) yy = -6;
        	else if(angle1>=-50&&angle1<=-20) yy = -6;
            else if(angle1>=30&&angle1<=60) yy = -6;
            else if(angle1>=120&&angle1<=150) yy = -6;
            else {
                if (dir.contains("north")) yy = -4;
                else if (dir.contains("south")) yy = -4;
                else if (dir.contains("east")) yy = -4;
                else if (dir.contains("west")) yy = -4;
                else yy = -4;
            }
        	zz *= 2;
        	xx *= 2;
        } else if(angle<=35&&angle>=25) {
        	if(angle1>=-150&&angle1<=-120) yy = -1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = -1.5;
            else if(angle1>=30&&angle1<=60) yy = -1.5;
            else if(angle1>=120&&angle1<=150) yy = -1.5;
            else {
                if (dir.contains("north")) yy = -1;
                else if (dir.contains("south")) yy = -1;
                else if (dir.contains("east")) yy = -1;
                else if (dir.contains("west")) yy = -1;
                else yy = -1;
            }
        } else if(angle<=20&&angle>=10) {
        	zz *= 1.5;
        	xx *= 1.5;
        	if(angle1>=-150&&angle1<=-120) yy = -1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = -1.5;
            else if(angle1>=30&&angle1<=60) yy = -1.5;
            else if(angle1>=120&&angle1<=150) yy = -1.5;
            else {
                if (dir.contains("north")) yy = -1;
                else if (dir.contains("south")) yy = -1;
                else if (dir.contains("east")) yy = -1;
                else if (dir.contains("west")) yy = -1;
                else yy = -1;
            }
        } else if(angle<=5&&angle>=-5) {
        	yy = -1;
        	zz *= 4;
        	xx *= 4;
        	if(angle1>=-150&&angle1<=-120) yy = -1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = -1.5;
            else if(angle1>=30&&angle1<=60) yy = -1.5;
            else if(angle1>=120&&angle1<=150) yy = -1.5;
            else {
                if (dir.contains("north")) yy = -1;
                else if (dir.contains("south")) yy = -1;
                else if (dir.contains("east")) yy = -1;
                else if (dir.contains("west")) yy = -1;
                else yy = -1;
            }
        } else if(angle<=-10&&angle>=-20) {
        	yy = 0;
        	string = "straight";
        } else if(angle<=-25&&angle>=-35) {
        	yy = 1;
        	zz *= 4;
        	xx *= 4;
        	if(angle1>=-150&&angle1<=-120) yy = 1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = 1.5;
            else if(angle1>=30&&angle1<=60) yy = 1.5;
            else if(angle1>=120&&angle1<=150) yy = 1.5;
            else {
                if (dir.contains("north")) yy = 1;
                else if (dir.contains("south")) yy = 1;
                else if (dir.contains("east")) yy = 1;
                else if (dir.contains("west")) yy = 1;
                else yy = 1;
            }
        } else if(angle<=-40&&angle>=-50) {
        	yy = 1;
        	zz *= 1.5;
        	xx *= 1.5;
        	if(angle1>=-150&&angle1<=-120) yy = 1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = 1.5;
            else if(angle1>=30&&angle1<=60) yy = 1.5;
            else if(angle1>=120&&angle1<=150) yy = 1.5;
            else {
                if (dir.contains("north")) yy = 1;
                else if (dir.contains("south")) yy = 1;
                else if (dir.contains("east")) yy = 1;
                else if (dir.contains("west")) yy = 1;
                else yy = 1;
            }
        } else if(angle<=-55&&angle>=-65) {
        	yy = 1;
        	if(angle1>=-150&&angle1<=-120) yy = 1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = 1.5;
            else if(angle1>=30&&angle1<=60) yy = 1.5;
            else if(angle1>=120&&angle1<=150) yy = 1.5;
            else {
                if (dir.contains("north")) yy = 1;
                else if (dir.contains("south")) yy = 1;
                else if (dir.contains("east")) yy = 1;
                else if (dir.contains("west")) yy = 1;
                else yy = 1;
            }
        } else if(angle<=-70&&angle>=-80) {
        	if(angle1>=-150&&angle1<=-120) yy = 6;
        	else if(angle1>=-50&&angle1<=-20) yy = 6;
            else if(angle1>=30&&angle1<=60) yy = 6;
            else if(angle1>=120&&angle1<=150) yy = 6;
            else {
                if (dir.contains("north")) yy = 4;
                else if (dir.contains("south")) yy = 4;
                else if (dir.contains("east")) yy = 4;
                else if (dir.contains("west")) yy = 4;
                else yy = 4;
            }
        	zz *= 2;
        	xx *= 2;
        } else if(angle<=-85&&angle>=-90) {
        	yy = 4;
        	if(angle1>=-150&&angle1<=-120) yy = 5;
        	else if(angle1>=-50&&angle1<=-20) yy = 5;
            else if(angle1>=30&&angle1<=60) yy = 5;
            else if(angle1>=120&&angle1<=150) yy = 5;
            else {
                if (dir.contains("north")) yy = 4;
                else if (dir.contains("south")) yy = 4;
                else if (dir.contains("east")) yy = 4;
                else if (dir.contains("west")) yy = 4;
                else yy = 4;
            }
        }
        
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(!string.equals("")&&Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
	}
	
	private void rotateUpAlt(Vec3d pos) {
        double xx = 0, yy = 0, zz = 0;
        double angle1 = (int)client.player.getRotationClient().y;
        String string = ""; 
        
        while(angle1>=360) angle1 -= 360;
        while(angle1<=-360) angle1 += 360;
        
        String dir = client.player.getHorizontalFacing().asString();
        dir = dir.toLowerCase().trim();
        
        double angle = (int)client.player.getRotationClient().x;
        
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
            if (dir.contains("north")) zz = -1;
            else if (dir.contains("south")) zz = 1;
            else if (dir.contains("east")) xx = 1;
            else if (dir.contains("west")) xx = -1;
            else xx = 1;
        }
        
        if(angle<=90&&angle>=85) {
        	yy = -4;
        	if(angle1>=-150&&angle1<=-120) yy = -5;
        	else if(angle1>=-50&&angle1<=-20) yy = -5;
            else if(angle1>=30&&angle1<=60) yy = -5;
            else if(angle1>=120&&angle1<=150) yy = -5;
            else {
                if (dir.contains("north")) yy = -4;
                else if (dir.contains("south")) yy = -4;
                else if (dir.contains("east")) yy = -4;
                else if (dir.contains("west")) yy = -4;
                else yy = -4;
            }
        } else if(angle<=80&&angle>=70) {
        	if(angle1>=-150&&angle1<=-120) yy = -6;
        	else if(angle1>=-50&&angle1<=-20) yy = -6;
            else if(angle1>=30&&angle1<=60) yy = -6;
            else if(angle1>=120&&angle1<=150) yy = -6;
            else {
                if (dir.contains("north")) yy = -4;
                else if (dir.contains("south")) yy = -4;
                else if (dir.contains("east")) yy = -4;
                else if (dir.contains("west")) yy = -4;
                else yy = -4;
            }
        	zz *= 2;
        	xx *= 2;
        } else if(angle<=65&&angle>=55) {
        	if(angle1>=-150&&angle1<=-120) yy = -1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = -1.5;
            else if(angle1>=30&&angle1<=60) yy = -1.5;
            else if(angle1>=120&&angle1<=150) yy = -1.5;
            else {
                if (dir.contains("north")) yy = -1;
                else if (dir.contains("south")) yy = -1;
                else if (dir.contains("east")) yy = -1;
                else if (dir.contains("west")) yy = -1;
                else yy = -1;
            }
        } else if(angle<=50&&angle>=40) {
        	zz *= 1.5;
        	xx *= 1.5;
        	if(angle1>=-150&&angle1<=-120) yy = -1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = -1.5;
            else if(angle1>=30&&angle1<=60) yy = -1.5;
            else if(angle1>=120&&angle1<=150) yy = -1.5;
            else {
                if (dir.contains("north")) yy = -1;
                else if (dir.contains("south")) yy = -1;
                else if (dir.contains("east")) yy = -1;
                else if (dir.contains("west")) yy = -1;
                else yy = -1;
            }
        } else if(angle<=35&&angle>=25) {
        	yy = -1;
        	zz *= 4;
        	xx *= 4;
        	if(angle1>=-150&&angle1<=-120) yy = -1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = -1.5;
            else if(angle1>=30&&angle1<=60) yy = -1.5;
            else if(angle1>=120&&angle1<=150) yy = -1.5;
            else {
                if (dir.contains("north")) yy = -1;
                else if (dir.contains("south")) yy = -1;
                else if (dir.contains("east")) yy = -1;
                else if (dir.contains("west")) yy = -1;
                else yy = -1;
            }
        } else if(angle<=20&&angle>=10) {
        	yy = 0;
        	string = "straight";
        } else if(angle<=5&&angle>=-5) {
        	yy = 1;
        	zz *= 4;
        	xx *= 4;
        	if(angle1>=-150&&angle1<=-120) yy = 1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = 1.5;
            else if(angle1>=30&&angle1<=60) yy = 1.5;
            else if(angle1>=120&&angle1<=150) yy = 1.5;
            else {
                if (dir.contains("north")) yy = 1;
                else if (dir.contains("south")) yy = 1;
                else if (dir.contains("east")) yy = 1;
                else if (dir.contains("west")) yy = 1;
                else yy = 1;
            }
        } else if(angle<=-10&&angle>=-20) {
        	yy = 1;
        	zz *= 1.5;
        	xx *= 1.5;
        	if(angle1>=-150&&angle1<=-120) yy = 1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = 1.5;
            else if(angle1>=30&&angle1<=60) yy = 1.5;
            else if(angle1>=120&&angle1<=150) yy = 1.5;
            else {
                if (dir.contains("north")) yy = 1;
                else if (dir.contains("south")) yy = 1;
                else if (dir.contains("east")) yy = 1;
                else if (dir.contains("west")) yy = 1;
                else yy = 1;
            }
        } else if(angle<=-25&&angle>=-35) {
        	yy = 1;
        	if(angle1>=-150&&angle1<=-120) yy = 1.5;
        	else if(angle1>=-50&&angle1<=-20) yy = 1.5;
            else if(angle1>=30&&angle1<=60) yy = 1.5;
            else if(angle1>=120&&angle1<=150) yy = 1.5;
            else {
                if (dir.contains("north")) yy = 1;
                else if (dir.contains("south")) yy = 1;
                else if (dir.contains("east")) yy = 1;
                else if (dir.contains("west")) yy = 1;
                else yy = 1;
            }
        } else if(angle<=-40&&angle>=-50) {
        	if(angle1>=-150&&angle1<=-120) yy = 6;
        	else if(angle1>=-50&&angle1<=-20) yy = 6;
            else if(angle1>=30&&angle1<=60) yy = 6;
            else if(angle1>=120&&angle1<=150) yy = 6;
            else {
                if (dir.contains("north")) yy = 4;
                else if (dir.contains("south")) yy = 4;
                else if (dir.contains("east")) yy = 4;
                else if (dir.contains("west")) yy = 4;
                else yy = 4;
            }
        	zz *= 2;
        	xx *= 2;
        } else if(angle<=-55&&angle>=-65) {
        	yy = 4;
        	if(angle1>=-150&&angle1<=-120) yy = 5;
        	else if(angle1>=-50&&angle1<=-20) yy = 5;
            else if(angle1>=30&&angle1<=60) yy = 5;
            else if(angle1>=120&&angle1<=150) yy = 5;
            else {
                if (dir.contains("north")) yy = 4;
                else if (dir.contains("south")) yy = 4;
                else if (dir.contains("east")) yy = 4;
                else if (dir.contains("west")) yy = 4;
                else yy = 4;
            }
        } else if(angle<=-70&&angle>=-90) {
        	yy = 30;
        	string = "up";
        }
        
        Vec3d vec3d = new Vec3d(pos.x+xx , pos.y+yy, pos.z+zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(!string.equals("")&&Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText(string), true);
	}
    
	private void lookNorth(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x+0 , pos.y+0, pos.z-1);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText("north"), true);
    }
    
	private void lookSouth(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x+0 , pos.y+0, pos.z+1);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText("south"), true);
    }
    
    private void lookWest(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x-1 , pos.y+0, pos.z+0);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText("west"), true);
    }
    
    private void lookEast(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x+1 , pos.y+0, pos.z+0);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET,vec3d);
        if(Config.get(Config.getNarratorkey())) client.player.sendMessage(new LiteralText("east"), true);
    }
    
}
