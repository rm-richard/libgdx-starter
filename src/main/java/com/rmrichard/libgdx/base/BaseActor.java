package com.rmrichard.libgdx.base;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseActor extends Actor {
    public TextureRegion region;
    public Polygon boundingPolygon;

    public BaseActor() {
        super();
        region = new TextureRegion();
        boundingPolygon = null;
    }

    public void setTexture(Texture texture) {
        int w = texture.getWidth();
        int h = texture.getHeight();
        setWidth(w);
        setHeight(h);
        region.setRegion(texture);
    }

    public void act(float dt) {
        super.act(dt);
    }

    public void draw(Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if (isVisible()) {
            batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

    public void setRectangleBoundary() {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = { 0,0, w,0, w,h, 0,h };
        boundingPolygon = new Polygon(vertices);
        boundingPolygon.setOrigin(getOriginX(), getOriginY());
    }

    public void setEllipseBoundary() {
        final int n = 8;
        float w = getWidth();
        float h = getHeight();
        float[] vertices = new float[2*n];

        for (int i = 0; i < n; ++i) {
            float t = i * 6.28f / n;
            vertices[2*i] = w/2 * MathUtils.cos(t) + w/2;
            vertices[2*i+1] = h/2 * MathUtils.sin(t) + h/2;
        }
        boundingPolygon = new Polygon(vertices);
        boundingPolygon.setOrigin(getOriginX(), getOriginX());
    }

    public Polygon getBoundingPolygon() {
        boundingPolygon.setPosition(getX(), getY());
        boundingPolygon.setRotation(getRotation());
        return boundingPolygon;
    }

    public boolean overlaps(BaseActor other, boolean resolve) {
        Polygon poly1 = this.getBoundingPolygon();
        Polygon poly2 = other.getBoundingPolygon();

        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())) {
            return false;
        }

        Intersector.MinimumTranslationVector mvt = new Intersector.MinimumTranslationVector();
        boolean polyOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mvt);
        if (polyOverlap && resolve) {
            this.moveBy(mvt.normal.x * mvt.depth, mvt.normal.y * mvt.depth);
        }
        return polyOverlap && (mvt.depth > 0.5f);
    }

    public void copy(BaseActor original) {
        this.region = new TextureRegion(original.region);
        if (original.boundingPolygon != null) {
            this.boundingPolygon = new Polygon(original.getBoundingPolygon().getVertices());
            this.boundingPolygon.setOrigin(original.getOriginX(), original.getOriginY());
        }
        this.setPosition(original.getX(), original.getY());
        this.setOriginX(original.getOriginX());
        this.setOriginY(original.getOriginY());
        this.setWidth(original.getWidth());
        this.setHeight(original.getHeight());
        this.setColor(original.getColor());
        this.setVisible(original.isVisible());
    }

    public BaseActor clone() {
        BaseActor newbie = new BaseActor();
        newbie.copy(this);
        return newbie;
    }
}
