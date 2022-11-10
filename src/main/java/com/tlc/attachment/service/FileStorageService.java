package com.tlc.attachment.service;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * <p>
 *     Provides file storage services.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public interface FileStorageService {

    /**
     * <p>
     *     Uploads the file in the path.
     * </p>
     *
     * @param inputStream represents the {@link InputStream} which contains the object data
     * @param extension   represents the file type
     * @param suffixPath  represents the path in which the file uploaded
     * @return the uploaded path of the file
     */
    Path uploadFile(final InputStream inputStream, final String extension, final String suffixPath);

}
