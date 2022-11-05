package com.tlc.attachment.service;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * <p>
 *     Provides file storage service.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public interface FileStorageService {

    /**
     * <p>
     *     Uploads the file with the given credentials.
     * </p>
     *
     * @param inputStream represents the {@link java.io.InputStream} that contains the object data
     * @param extension   represents the file type
     * @param suffixPath  represents the directory path
     * @return the suffix path of the file
     */
    Path uploadFile(final InputStream inputStream, final String extension, final String suffixPath);

}
