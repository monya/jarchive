package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;

import com.github.jamescarter.jarchive.JFile;
import com.github.junrar.Archive;
import com.github.junrar.Volume;
import com.github.junrar.VolumeManager;

public class StreamVolumeManager implements VolumeManager {
	private JFile file;
	private InputStream is;

	public StreamVolumeManager(JFile file, InputStream is) {
		this.file = file;
		this.is = is;
	}

	@Override
	public Volume nextArchive(Archive arc, Volume arg1) throws IOException {
		return new StreamVolume(file, arc, is);
	}
}
