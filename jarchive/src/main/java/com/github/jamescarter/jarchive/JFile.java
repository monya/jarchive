package com.github.jamescarter.jarchive;

import java.io.File;

public class JFile {
	private String name;
	private Long compressedSize;
	private Long size;
	private boolean isDir;
	private Class<?> streamClass;

	public JFile(File file) {
		this(file.getAbsolutePath(), file.length(), file.length(), file.isDirectory());
	}
	
	public JFile(String name, Long compressedSize, Long size, boolean isDir) {
		this.name = name;
		this.compressedSize = compressedSize;
		this.size = size;
		this.isDir = isDir;
		this.streamClass = JServiceLocator.getFirstSupportedService(name);
	}

	public String getName() {
		return name;
	}

	public Long getCompressedSize() {
		return compressedSize;
	}

	public Long getSize() {
		return size;
	}

	public Class<?> getStreamClass() {
		return streamClass;
	}

	public boolean isDirectory() {
		return isDir;
	}

	public boolean isArchive() {
		return streamClass != null;
	}

	@Override
	public String toString() {
		return "[" +
			"name=" + getName() + ", " +
			"compressedSize=" + getCompressedSize() + ", " +
			"size=" + getSize() + ", " +
			"isDir=" + isDirectory() + ", " +
			"isArchive=" + isArchive() + ", " +
			"streamClass=" + getStreamClass() +
		"]";
	}
}
