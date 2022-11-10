package com.tlc.attachment.io;

import java.io.InputStream;

/**
 * <p>
 *     {@code File} which contains the file details.
 * </p>
 *
 * @param inputStream represents an {@link java.io.InputStream} which contains the object data
 * @param name        represents the name of the file
 * @param extension   represents the file type
 * @param size        represents the file size
 * @param mediaType   indicates the standard MIME type describing the format of the contents
 *
 * @author Sundar
 * @version 1.0
 */
public record File(InputStream inputStream, String name, String extension, Long size, String mediaType) {

}
