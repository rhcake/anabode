package com.anabode.util;

/**
 * @author Modris Vekmanis
 */
public class Constants {
    public static String WELCOME_SCREEN = "WelcomeScreen";
    public static String MENU_SCREEN = "MenuScreen";

    public static final int CONSTRUCTION_OBJECT_RENDER_LAYER = 2;
    public static final int ENVIRONMENT_OBJECT_RENDER_LAYER = 1;
    public static final int BACK_GROUND_RENDER_LAYER = 0;

    public static final String MATERIAL_SPRITE_KEY = "materialTexture";
    public static final String MATERIAL_SPRITE_ORIGIN_KEY = "materialOrigin";
    public static final String MATERIAL_POS_KEY = "materialPosition";
    public static final String MATERIAL_DIMENSION_KEY = "materialDimension";
    public static final String MATERIAL_BODY_KEY = "materialBody";
    public static final String MATERIAL_ANGLE_KEY = "materialAngle";


    public static final String WOOD_BOARD_IMAGE = "woodboard";

    public static final String SHAFT_WALL_IMAGE = "shaftWall";


    public static final short CONSTRUCTION_OBJECT_COLLISION_CATEGORY = 0x0001; // 0000 0001

    public static final short CONSTRUCTION_LADDER_COLLISION_CATEGORY = 0x0002; // 0000 0010

    public static final short PLAYER_COLLISION_CATEGORY = 0x0004; // 0000 0100

    public static final short ENVIRONMENT_COLLISION_CATEGORY = 0x0008; // 0000 1000


    public static final short CONSTRUCTION_OBJECT_COLLISION_MASK = CONSTRUCTION_OBJECT_COLLISION_CATEGORY | ENVIRONMENT_COLLISION_CATEGORY; // 0000 1001

    public static final short CONSTRUCTION_LADDER_COLLISION_MASK = CONSTRUCTION_LADDER_COLLISION_CATEGORY | PLAYER_COLLISION_CATEGORY | ENVIRONMENT_COLLISION_CATEGORY; // 0000 1110

    public static final short PLAYER_COLLISION_MASK = CONSTRUCTION_LADDER_COLLISION_CATEGORY | PLAYER_COLLISION_CATEGORY | ENVIRONMENT_COLLISION_CATEGORY; // 0000 1110

    public static final short ENVIRONMENT_COLLISION_MASK = CONSTRUCTION_LADDER_COLLISION_CATEGORY | PLAYER_COLLISION_CATEGORY | ENVIRONMENT_COLLISION_CATEGORY | CONSTRUCTION_OBJECT_COLLISION_CATEGORY; // 0000 1111
}
