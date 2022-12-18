package com.mygdx.tankgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Projectile extends GameObject {
    public Body pBody;
    public PolygonShape pShape;
    public Vector2[] pVertices = {new Vector2(0f, 0f), new Vector2(0f, 0.1f), new Vector2(0.1f, 0.1f), new Vector2(0.1f, 0f)};


    public Projectile(Position p) {
        super(p);
        pShape = new PolygonShape();
        pShape.set(pVertices);

    }
    public Position projectilePath(Weapon weapon,  Position initPos,  int power,   int angle){
        // decorates the path somehow and returns the FINALPOS

        return initPos;
    }

    public void pPath(float power, float angle, Position initPos){
        String vertexShader = "attribute vec4 a_position;\n" +
                "attribute vec4 a_color;\n" +
                "attribute vec2 a_texCoord0;\n" +
                "uniform mat4 u_projTrans;\n" +
                "varying vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "void main() {\n" +
                "v_color = vec4(1, 1, 1, 1);\n" +
                "v_texCoords = a_texCoord0;\n" +
                "gl_Position =  u_projTrans * a_position;\n" +
                "}";
        String fragmentShader = "#ifdef GL_ES\n" +
                "precision mediump float;\n" +
                "#endif\n" +
                "varying vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "uniform sampler2D u_texture;\n" +
                "void main() {\n" +
                "gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" +
                "}";
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        Mesh mesh = new Mesh(true, 4, 0, new VertexAttribute( VertexAttribute.Position().usage, 3, ShaderProgram.POSITION_ATTRIBUTE ),
                new VertexAttribute( VertexAttributes.Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0" ));
        mesh.setVertices(new float[] {
                -0.5f, -0.5f, 0, 1, 1, 1, 1, 0, 1,
                0.5f, -0.5f, 0, 1, 1, 1, 1, 1, 1,
                0.5f, 0.5f, 0, 1, 1, 1, 1, 1, 0,
                -0.5f, 0.5f, 0, 1, 1, 1, 1, 0, 0
        });

        mesh.render( shader, GL20.GL_LINES );
    }

}
