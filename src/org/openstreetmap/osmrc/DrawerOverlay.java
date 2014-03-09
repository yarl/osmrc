package org.openstreetmap.osmrc;

import java.awt.Graphics;

public interface DrawerOverlay {
	public void draw(Graphics g, ZMapWidget map);
}
