package org.openstreetmap.osmrc;

import java.io.Serializable;

import org.openstreetmap.osmrc.DataContainer.Changeset;
import org.openstreetmap.osmrc.DataContainer.Node;
import org.openstreetmap.osmrc.DataContainer.Way;

public interface MapFilter extends Serializable{
	public boolean nodeFilter(Node node);
	public boolean wayFilter(Way way);
	public boolean changesetFilter(Changeset chs);
	
}
