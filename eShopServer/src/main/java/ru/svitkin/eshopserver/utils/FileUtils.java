package ru.svitkin.eshopserver.utils;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {
	public String getExtension(String fileName) {
		if (fileName == null)
			return "";

		int lastIndexOf = fileName.lastIndexOf(".");
		if (lastIndexOf == -1)
			return "";

		return fileName.substring(lastIndexOf);
	}
}
