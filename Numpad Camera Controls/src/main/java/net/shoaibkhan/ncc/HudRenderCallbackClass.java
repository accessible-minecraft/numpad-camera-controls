package net.shoaibkhan.ncc;

import java.util.Locale;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.text.Text;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.shoaibkhan.ncc.config.Config;

@Environment(EnvType.CLIENT)
public class HudRenderCallbackClass {
    private MinecraftClient client;
    private NarratorManager narratorManager;

    public HudRenderCallbackClass() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            this.client = client;
            this.narratorManager = NarratorManager.INSTANCE;
            if (client.player != null && client.currentScreen == null) {
                try {
                    Vec3d pos = client.player.getPos();
                    boolean isAltPressed = (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), InputUtil.fromTranslationKey("key.keyboard.left.alt").getCode()) || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), InputUtil.fromTranslationKey("key.keyboard.right.alt").getCode()));

                    if (isAltPressed) {
                        while (ClientInitializer.centreAxis.wasPressed()) {
                            centreAxisReverse(pos);
                        }

                        while (ClientInitializer.rotateRight.wasPressed()) {
                            rotateRightAlt(pos);
                        }

                        while (ClientInitializer.rotateLeft.wasPressed()) {
                            rotateLeftAlt(pos);
                        }

                        while (ClientInitializer.rotateDownwards.wasPressed()) {
                            rotateDownAlt(pos);
                        }

                        while (ClientInitializer.rotateUp.wasPressed()) {
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

                        while (ClientInitializer.rotateDownwards.wasPressed()) {
                            rotateDown(pos);
                        }

                        while (ClientInitializer.rotateUp.wasPressed()) {
                            rotateUp(pos);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void narrate(String key) {
        if (key.trim().equals(""))
            return;

        if (Config.get(Config.getNarratorkey())) {
            key = "narrate.nccbyshoaibkhan." + key;
            narratorManager.narrate(I18n.translate(key));
        }
    }

    private void narrateDirection(Direction dir) {
        narrate(dir.asString().toLowerCase(Locale.ENGLISH));
    }

    private void centreAxisReverse(Vec3d pos) {
        assert client.player != null;
        Direction dir = client.player.getHorizontalFacing().getOpposite();
        Vec3f unit = dir.getUnitVector();
        Vec3d vec3d = pos.add(unit.getX(), unit.getY(), unit.getZ());
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        narrateDirection(dir);
    }

    private void centreAxis(Vec3d pos) {
        assert client.player != null;
        Direction dir = client.player.getHorizontalFacing();
        Vec3f unit = dir.getUnitVector();
        Vec3d vec3d = pos.add(unit.getX(), unit.getY(), unit.getZ());
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        narrateDirection(dir);
    }

    private void rotateRight(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        assert client.player != null;
        int angle = (int) client.player.getRotationClient().y;
        String string = "";
        while (angle >= 360)
            angle -= 360;
        while (angle <= -360)
            angle += 360;
        if ((angle >= -150 && angle <= -120) || (angle >= 210 && angle <= 240)) {
            // Look east
            xx = 1;
            string = "east";
        } else if ((angle >= -60 && angle <= -30) || (angle >= 300 && angle <= 330)) {
            // Look south
            zz = 1;
            string = "south";
        } else if ((angle >= 30 && angle <= 60) || (angle >= -330 && angle <= -300)) {
            // Look west
            xx = -1;
            string = "west";
        } else if ((angle >= 120 && angle <= 150) || (angle >= -240 && angle <= -210)) {
            // Look north
            zz = -1;
            string = "north";
        } else {
            Direction dir = client.player.getHorizontalFacing();
            if (dir == Direction.NORTH) {
                // Look north-east
                zz = -1;
                xx = 1;
                string = "north-east";
            } else if (dir == Direction.SOUTH) {
                // Look south-west
                zz = 1;
                xx = -1;
                string = "south-west";
            } else if (dir == Direction.EAST) {
                // Look south-east
                zz = 1;
                xx = 1;
                string = "south-east";
            } else if (dir == Direction.WEST) {
                // Look north-west
                zz = -1;
                xx = -1;
                string = "north-west";
            }
        }
        Vec3d vec3d = new Vec3d(pos.x + xx, pos.y + yy, pos.z + zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        narrate(string);
    }

    private void rotateLeft(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        assert client.player != null;
        int angle = (int) client.player.getRotationClient().y;
        String string = "";
        while (angle >= 360)
            angle -= 360;
        while (angle <= -360)
            angle += 360;
        if ((angle >= -150 && angle <= -120) || (angle >= 210 && angle <= 240)) {
            // Look north
            zz = -1;
            string = "north";
        } else if ((angle >= -60 && angle <= -30) || (angle >= 300 && angle <= 330)) {
            // Look east
            xx = 1;
            string = "east";
        } else if (angle >= 30 && angle <= 60 || angle >= -330 && angle <= -300) {
            // Look south
            zz = 1;
            string = "south";
        } else if ((angle >= 120 && angle <= 150) || (angle >= -240 && angle <= -210)) {
            // Look west
            xx = -1;
            string = "west";
        } else {
            Direction dir = client.player.getHorizontalFacing();
            if (dir == Direction.NORTH) {
                // Look north-west
                zz = -1;
                xx = -1;
                string = "north-west";
            } else if (dir == Direction.SOUTH) {
                // Look south-east
                zz = 1;
                xx = 1;
                string = "south-east";
            } else if (dir == Direction.EAST) {
                // Look north-east
                zz = -1;
                xx = 1;
                string = "north-east";
            } else if (dir == Direction.WEST) {
                // Look south-west
                zz = 1;
                xx = -1;
                string = "south-west";
            }
        }
        Vec3d vec3d = new Vec3d(pos.x + xx, pos.y + yy, pos.z + zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        narrate(string);
    }

    private void rotateDown(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        assert client.player != null;
        int angle1 = (int) client.player.getRotationClient().y;
        String string = "";
        while (angle1 >= 360)
            angle1 -= 360;
        while (angle1 <= -360)
            angle1 += 360;

        if (angle1 >= -150 && angle1 <= -120) {
            // Looking North East
            zz = -1;
            xx = 1;
        } else if (angle1 >= -50 && angle1 <= -20) {
            // Looking South East
            zz = 1;
            xx = 1;
        } else if (angle1 >= 30 && angle1 <= 60) {
            // Looking South West
            zz = 1;
            xx = -1;
        } else if (angle1 >= 120 && angle1 <= 150) {
            // Looking North West
            zz = -1;
            xx = -1;
        } else {
            Direction dir = client.player.getHorizontalFacing();
            if (dir == Direction.NORTH)
                zz = -1;
            else if (dir == Direction.SOUTH)
                zz = 1;
            else if (dir == Direction.EAST)
                xx = 1;
            else if (dir == Direction.WEST)
                xx = -1;
        }

        int angle = (int) client.player.getRotationClient().x;

        if (angle <= 90 && angle >= 60) {
            string = "down";
            yy = -30;
        } else if (angle < 60 && angle >= 30) {
            string = "down";
            yy = -30;
        } else if (angle < 30 && angle >= 0) {
            yy = -1;
        } else if (angle < 0 && angle >= -30) {
            string = "straight";
        } else if (angle < -30 && angle >= -60) {
            string = "straight";
        } else if (angle < -60 && angle >= -90) {
            yy = 1;
        }

        Vec3d vec3d = new Vec3d(pos.x + xx, pos.y + yy, pos.z + zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        if (!string.isEmpty())
            narrate(string);
    }

    private void rotateUp(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        assert client.player != null;
        int angle1 = (int) client.player.getRotationClient().y;
        String string = "";
        while (angle1 >= 360)
            angle1 -= 360;
        while (angle1 <= -360)
            angle1 += 360;

        if (angle1 >= -150 && angle1 <= -120) {
            // Looking North East
            zz = -1;
            xx = 1;
        } else if (angle1 >= -50 && angle1 <= -20) {
            // Looking South East
            zz = 1;
            xx = 1;
        } else if (angle1 >= 30 && angle1 <= 60) {
            // Looking South West
            zz = 1;
            xx = -1;
        } else if (angle1 >= 120 && angle1 <= 150) {
            // Looking North West
            zz = -1;
            xx = -1;
        } else {
            Direction dir = client.player.getHorizontalFacing();
            if (dir == Direction.NORTH)
                zz = -1;
            else if (dir == Direction.SOUTH)
                zz = 1;
            else if (dir == Direction.EAST)
                xx = 1;
            else if (dir == Direction.WEST)
                xx = -1;
            else
                xx = 1;
        }
        int angle = (int) client.player.getRotationClient().x;

        if (angle < 90 && angle >= 60) {
            yy = -1;
        } else if (angle < 60 && angle >= 30) {
            string = "straight";
        } else if (angle < 30 && angle >= 10) {
            string = "straight";
        } else if (angle < 10 && angle >= -30) {
            yy = 1;
        } else if (angle < -30 && angle >= -60) {
            string = "up";
            yy = 30;
        } else if (angle < -60 && angle >= -90) {
            string = "up";
            yy = 30;
        }

        Vec3d vec3d = new Vec3d(pos.x + xx, pos.y + yy, pos.z + zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        if (!string.isEmpty())
            narrate(string);
    }

    private void rotateRightAlt(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        assert client.player != null;
        int angle = (int) client.player.getRotationClient().y;
        String string = "";
        while (angle >= 360)
            angle -= 360;
        while (angle <= -360)
            angle += 360;

        if (angle >= -170 && angle <= -160) {
            zz = -2;
            xx = 1;
        } else if (angle >= -80 && angle <= -70) {
            zz = 1;
            xx = 2;
        } else if (angle >= 10 && angle <= 20) {
            zz = 2;
            xx = -1;
        } else if (angle >= 100 && angle <= 110) {
            zz = -1;
            xx = -2;
        } else if (angle >= -155 && angle <= -145) {
            // Look north-east
            zz = -1;
            xx = 1;
            string = "north-east";
        } else if (angle >= -65 && angle <= -55) {
            // Look south-east
            zz = 1;
            xx = 1;
            string = "south-east";
        } else if (angle >= 25 && angle <= 35) {
            // Look south-west
            zz = 1;
            xx = -1;
            string = "south-west";
        } else if (angle >= 115 && angle <= 125) {
            // Look north-west
            zz = -1;
            xx = -1;
            string = "north-west";
        } else if (angle >= -140 && angle <= -130) {
            // Looking north-east
            zz = -1;
            xx = 2;
        } else if (angle >= -50 && angle <= -40) {
            // Looking south-east
            zz = 2;
            xx = 1;
        } else if (angle >= 40 && angle <= 50) {
            // Looking south-west
            zz = 1;
            xx = -2;
        } else if ((angle >= 120 && angle <= 150) || (angle >= -240 && angle <= -210)) {
            // Looking north-west
            zz = -2;
            xx = -1;
        } else if (angle >= -125 && angle <= -115) {
            zz = -1;
            xx = 4;
        } else if (angle >= -35 && angle <= -25) {
            zz = 4;
            xx = 1;
        } else if (angle >= 55 && angle <= 65) {
            zz = 1;
            xx = -4;
        } else if (angle >= 145 && angle <= 155) {
            zz = -4;
            xx = -1;
        } else if (angle >= -110 && angle <= -100) {
            // Look east
            xx = 1;
            string = "east";
        } else if (angle >= -20 && angle <= -10) {
            // Look south
            zz = 1;
            string = "south";
        } else if (angle >= 70 && angle <= 80) {
            // Look west
            xx = -1;
            string = "west";
        } else if (angle >= 160 && angle <= 170) {
            // Look north
            zz = -1;
            string = "north";
        } else {
            Direction dir = client.player.getHorizontalFacing();
            if (dir == Direction.NORTH) {
                zz = -4;
                xx = 1;
            } else if (dir == Direction.SOUTH) {
                zz = 4;
                xx = -1;
            } else if (dir == Direction.EAST) {
                zz = 1;
                xx = 4;
            } else if (dir == Direction.WEST) {
                zz = -1;
                xx = -4;
            }
        }
        Vec3d vec3d = new Vec3d(pos.x + xx, pos.y + yy, pos.z + zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        narrate(string);
    }

    private void rotateLeftAlt(Vec3d pos) {
        int xx = 0, yy = 0, zz = 0;
        assert client.player != null;
        int angle = (int) client.player.getRotationClient().y;
        String string = "";
        while (angle >= 360)
            angle -= 360;
        while (angle <= -360)
            angle += 360;

        if (angle >= -170 && angle <= -160) {
            // Look north
            zz = -1;
            string = "north";
        } else if (angle >= -80 && angle <= -70) {
            // Look east
            xx = 1;
            string = "east";
        } else if (angle >= 10 && angle <= 20) {
            // Look south
            zz = 1;
            string = "south";
        } else if (angle >= 100 && angle <= 110) {
            // Look west
            xx = -1;
            string = "west";
        } else if (angle >= -155 && angle <= -145) {
            zz = -4;
            xx = 1;
        } else if (angle >= -65 && angle <= -55) {
            zz = 1;
            xx = 4;
        } else if (angle >= 25 && angle <= 35) {
            zz = 4;
            xx = -1;
        } else if (angle >= 115 && angle <= 125) {
            zz = -1;
            xx = -4;
        } else if (angle >= -140 && angle <= -130) {
            // Looking north-east
            zz = -2;
            xx = 1;
        } else if (angle >= -50 && angle <= -40) {
            // Looking south-east
            zz = 1;
            xx = 2;
        } else if (angle >= 40 && angle <= 50) {
            // Looking south-west
            zz = 2;
            xx = -1;
        } else if ((angle >= 120 && angle <= 150) || (angle >= -240 && angle <= -210)) {
            // Looking north-west
            zz = -1;
            xx = -2;
        } else if (angle >= -125 && angle <= -115) {
            // Look north-east
            zz = -1;
            xx = 1;
            string = "north-east";
        } else if (angle >= -35 && angle <= -25) {
            // Look south-east
            zz = 1;
            xx = 1;
            string = "south-east";
        } else if (angle >= 55 && angle <= 65) {
            // Look south-west
            zz = 1;
            xx = -1;
            string = "south-west";
        } else if (angle >= 145 && angle <= 155) {
            // Look north-west
            zz = -1;
            xx = -1;
            string = "north-west";
        } else if (angle >= -110 && angle <= -100) {
            zz = -1;
            xx = 2;
        } else if (angle >= -20 && angle <= -10) {
            zz = 2;
            xx = 1;
        } else if (angle >= 70 && angle <= 80) {
            zz = 1;
            xx = -2;
        } else if (angle >= 160 && angle <= 170) {
            zz = -2;
            xx = -1;
        } else {
            Direction dir = client.player.getHorizontalFacing();
            if (dir == Direction.NORTH) {
                zz = -4;
                xx = -1;
            } else if (dir == Direction.SOUTH) {
                zz = 4;
                xx = 1;
            } else if (dir == Direction.EAST) {
                zz = -1;
                xx = 4;
            } else if (dir == Direction.WEST) {
                zz = 1;
                xx = -4;
            }
        }
        Vec3d vec3d = new Vec3d(pos.x + xx, pos.y + yy, pos.z + zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        narrate(string);
    }

    private void rotateDownAlt(Vec3d pos) {
        double xx = 0, yy = 0, zz = 0;
        assert client.player != null;
        double angle1 = (int) client.player.getRotationClient().y;
        String string = "";

        while (angle1 >= 360)
            angle1 -= 360;
        while (angle1 <= -360)
            angle1 += 360;

        Direction dir = client.player.getHorizontalFacing();

        double angle = (int) client.player.getRotationClient().x;

        if (angle1 >= -150 && angle1 <= -120) {
            // Looking North East
            zz = -1;
            xx = 1;
        } else if (angle1 >= -50 && angle1 <= -20) {
            // Looking South East
            zz = 1;
            xx = 1;
        } else if (angle1 >= 30 && angle1 <= 60) {
            // Looking South West
            zz = 1;
            xx = -1;
        } else if (angle1 >= 120 && angle1 <= 150) {
            // Looking North West
            zz = -1;
            xx = -1;
        } else {
            if (dir == Direction.NORTH)
                zz = -1;
            else if (dir == Direction.SOUTH)
                zz = 1;
            else if (dir == Direction.EAST)
                xx = 1;
            else if (dir == Direction.WEST)
                xx = -1;
            else
                xx = 1;
        }

        if (angle <= 90 && angle >= 70) {
            yy = -30;
            string = "down";
        } else if (angle <= 65 && angle >= 55) {
            yy = -4;
            if (angle1 >= -150 && angle1 <= -120)
                yy = -5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -5;
        } else if (angle <= 50 && angle >= 40) {
            if (angle1 >= -150 && angle1 <= -120)
                yy = -6;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -6;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -6;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -6;
            else {
                if (dir == Direction.NORTH)
                    yy = -4;
                else if (dir == Direction.SOUTH)
                    yy = -4;
                else if (dir == Direction.EAST)
                    yy = -4;
                else if (dir == Direction.WEST)
                    yy = -4;
                else
                    yy = -4;
            }
            zz *= 2;
            xx *= 2;
        } else if (angle <= 35 && angle >= 25) {
            if (angle1 >= -150 && angle1 <= -120)
                yy = -1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -1.5;
            else {
                if (dir == Direction.NORTH)
                    yy = -1;
                else if (dir == Direction.SOUTH)
                    yy = -1;
                else if (dir == Direction.EAST)
                    yy = -1;
                else if (dir == Direction.WEST)
                    yy = -1;
                else
                    yy = -1;
            }
        } else if (angle <= 20 && angle >= 10) {
            zz *= 1.5;
            xx *= 1.5;
            if (angle1 >= -150 && angle1 <= -120)
                yy = -1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -1.5;
            else {
                if (dir == Direction.NORTH)
                    yy = -1;
                else if (dir == Direction.SOUTH)
                    yy = -1;
                else if (dir == Direction.EAST)
                    yy = -1;
                else if (dir == Direction.WEST)
                    yy = -1;
                else
                    yy = -1;
            }
        } else if (angle <= 5 && angle >= -5) {
            yy = -1;
            zz *= 4;
            xx *= 4;
            if (angle1 >= -150 && angle1 <= -120)
                yy = -1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -1.5;
        } else if (angle <= -10 && angle >= -20) {
            yy = 0;
            string = "straight";
        } else if (angle <= -25 && angle >= -35) {
            yy = 1;
            zz *= 4;
            xx *= 4;
            if (angle1 >= -150 && angle1 <= -120)
                yy = 1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 1.5;
        } else if (angle <= -40 && angle >= -50) {
            yy = 1;
            zz *= 1.5;
            xx *= 1.5;
            if (angle1 >= -150 && angle1 <= -120)
                yy = 1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 1.5;
        } else if (angle <= -55 && angle >= -65) {
            yy = 1;
            if (angle1 >= -150 && angle1 <= -120)
                yy = 1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 1.5;
        } else if (angle <= -70 && angle >= -80) {
            if (angle1 >= -150 && angle1 <= -120)
                yy = 6;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 6;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 6;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 6;
            else {
                if (dir == Direction.NORTH)
                    yy = 4;
                else if (dir == Direction.SOUTH)
                    yy = 4;
                else if (dir == Direction.EAST)
                    yy = 4;
                else if (dir == Direction.WEST)
                    yy = 4;
                else
                    yy = 4;
            }
            zz *= 2;
            xx *= 2;
        } else if (angle <= -85 && angle >= -90) {
            if (angle1 >= -150 && angle1 <= -120)
                yy = 5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 5;
            else {
                if (dir == Direction.NORTH)
                    yy = 4;
                else if (dir == Direction.SOUTH)
                    yy = 4;
                else if (dir == Direction.EAST)
                    yy = 4;
                else if (dir == Direction.WEST)
                    yy = 4;
                else
                    yy = 4;
            }
        }

        Vec3d vec3d = new Vec3d(pos.x + xx, pos.y + yy, pos.z + zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        if (!string.isEmpty())
            narrate(string);
    }

    private void rotateUpAlt(Vec3d pos) {
        double xx = 0, yy = 0, zz = 0;
        assert client.player != null;
        double angle1 = (int) client.player.getRotationClient().y;
        String string = "";

        while (angle1 >= 360)
            angle1 -= 360;
        while (angle1 <= -360)
            angle1 += 360;

        Direction dir = client.player.getHorizontalFacing();

        double angle = (int) client.player.getRotationClient().x;

        if (angle1 >= -150 && angle1 <= -120) {
            // Looking North East
            zz = -1;
            xx = 1;
        } else if (angle1 >= -50 && angle1 <= -20) {
            // Looking South East
            zz = 1;
            xx = 1;
        } else if (angle1 >= 30 && angle1 <= 60) {
            // Looking South West
            zz = 1;
            xx = -1;
        } else if (angle1 >= 120 && angle1 <= 150) {
            // Looking North West
            zz = -1;
            xx = -1;
        } else {
            if (dir == Direction.NORTH)
                zz = -1;
            else if (dir == Direction.SOUTH)
                zz = 1;
            else if (dir == Direction.EAST)
                xx = 1;
            else if (dir == Direction.WEST)
                xx = -1;
            else
                xx = 1;
        }

        if (angle <= 90 && angle >= 85) {
            if (angle1 >= -150 && angle1 <= -120)
                yy = -5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -5;
            else {
                if (dir == Direction.NORTH)
                    yy = -4;
                else if (dir == Direction.SOUTH)
                    yy = -4;
                else if (dir == Direction.EAST)
                    yy = -4;
                else if (dir == Direction.WEST)
                    yy = -4;
                else
                    yy = -4;
            }
        } else if (angle <= 80 && angle >= 70) {
            if (angle1 >= -150 && angle1 <= -120)
                yy = -6;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -6;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -6;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -6;
            else {
                if (dir == Direction.NORTH)
                    yy = -4;
                else if (dir == Direction.SOUTH)
                    yy = -4;
                else if (dir == Direction.EAST)
                    yy = -4;
                else if (dir == Direction.WEST)
                    yy = -4;
                else
                    yy = -4;
            }
            zz *= 2;
            xx *= 2;
        } else if (angle <= 65 && angle >= 55) {
            if (angle1 >= -150 && angle1 <= -120)
                yy = -1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -1.5;
            else {
                if (dir == Direction.NORTH)
                    yy = -1;
                else if (dir == Direction.SOUTH)
                    yy = -1;
                else if (dir == Direction.EAST)
                    yy = -1;
                else if (dir == Direction.WEST)
                    yy = -1;
                else
                    yy = -1;
            }
        } else if (angle <= 50 && angle >= 40) {
            zz *= 1.5;
            xx *= 1.5;
            if (angle1 >= -150 && angle1 <= -120)
                yy = -1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -1.5;
            else {
                if (dir == Direction.NORTH)
                    yy = -1;
                else if (dir == Direction.SOUTH)
                    yy = -1;
                else if (dir == Direction.EAST)
                    yy = -1;
                else if (dir == Direction.WEST)
                    yy = -1;
                else
                    yy = -1;
            }
        } else if (angle <= 35 && angle >= 25) {
            zz *= 4;
            xx *= 4;
            if (angle1 >= -150 && angle1 <= -120)
                yy = -1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = -1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = -1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = -1.5;
            else {
                if (dir == Direction.NORTH)
                    yy = -1;
                else if (dir == Direction.SOUTH)
                    yy = -1;
                else if (dir == Direction.EAST)
                    yy = -1;
                else if (dir == Direction.WEST)
                    yy = -1;
                else
                    yy = -1;
            }
        } else if (angle <= 20 && angle >= 10) {
            yy = 0;
            string = "straight";
        } else if (angle <= 5 && angle >= -5) {
            yy = 1;
            zz *= 4;
            xx *= 4;
            if (angle1 >= -150 && angle1 <= -120)
                yy = 1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 1.5;
        } else if (angle <= -10 && angle >= -20) {
            yy = 1;
            zz *= 1.5;
            xx *= 1.5;
            if (angle1 >= -150 && angle1 <= -120)
                yy = 1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 1.5;
        } else if (angle <= -25 && angle >= -35) {
            yy = 1;
            if (angle1 >= -150 && angle1 <= -120)
                yy = 1.5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 1.5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 1.5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 1.5;
        } else if (angle <= -40 && angle >= -50) {
            if (angle1 >= -150 && angle1 <= -120)
                yy = 6;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 6;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 6;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 6;
            else {
                if (dir == Direction.NORTH)
                    yy = 4;
                else if (dir == Direction.SOUTH)
                    yy = 4;
                else if (dir == Direction.EAST)
                    yy = 4;
                else if (dir == Direction.WEST)
                    yy = 4;
                else
                    yy = 4;
            }
            zz *= 2;
            xx *= 2;
        } else if (angle <= -55 && angle >= -65) {
            yy = 4;
            if (angle1 >= -150 && angle1 <= -120)
                yy = 5;
            else if (angle1 >= -50 && angle1 <= -20)
                yy = 5;
            else if (angle1 >= 30 && angle1 <= 60)
                yy = 5;
            else if (angle1 >= 120 && angle1 <= 150)
                yy = 5;
        } else if (angle <= -70 && angle >= -90) {
            yy = 30;
            string = "up";
        }

        Vec3d vec3d = new Vec3d(pos.x + xx, pos.y + yy, pos.z + zz);
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        if (!string.isEmpty())
            narrate(string);
    }

    private void lookNorth(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x + 0, pos.y + 0, pos.z - 1);
        assert client.player != null;
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        if (Config.get(Config.getNarratorkey()))
            client.player.sendMessage(Text.of(I18n.translate("narrate.nccbyshoaibkhan.north")), true);
    }

    private void lookSouth(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x + 0, pos.y + 0, pos.z + 1);
        assert client.player != null;
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        if (Config.get(Config.getNarratorkey()))

            client.player.sendMessage(Text.of(I18n.translate("narrate.nccbyshoaibkhan.south")), true);
    }

    private void lookWest(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x - 1, pos.y + 0, pos.z + 0);
        assert client.player != null;
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        if (Config.get(Config.getNarratorkey()))
            client.player.sendMessage(Text.of(I18n.translate("narrate.nccbyshoaibkhan.west")), true);
    }

    private void lookEast(Vec3d pos) {
        Vec3d vec3d = new Vec3d(pos.x + 1, pos.y + 0, pos.z + 0);
        assert client.player != null;
        client.player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, vec3d);
        if (Config.get(Config.getNarratorkey()))
            client.player.sendMessage(Text.of(I18n.translate("narrate.nccbyshoaibkhan.east")), true);
    }

}
