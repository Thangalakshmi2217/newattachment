package com.tlc.attachment.io;

import java.io.InputStream;

/**
 * <p>
 *     {@code File} contains the object data related information.
 * </p>
 *
 * @param inputStream represents the {@link java.io.InputStream} that contains the object data
 * @param name        represents the name of the file
 * @param extension   represents the file type
 * @param size        represents the file size
 * @param mediaType   indicates the file media type (MIME type - media type)
 *
 * @author Sundar
 * @version 1.0
 */
public record File(InputStream inputStream, String name, String extension, Long size, String mediaType) {

}
