package org.openstreetmap.osmrc;

import org.openstreetmap.osmrc.Configuration.Profile;

public interface ConfigurationListener {
	public void profileChanged(Profile p);
}
