package com.tlc.attachment.service;

/**
 * <p>
 *     Provides storage services.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public interface StorageService {

    /**
     * <p>
     *     Gets the object storage service.
     * </p>
     *
     * @return the {@link ObjectStorageService} object
     */
    ObjectStorageService getObjectStorageService();

    /**
     * <p>
     *     Gets the file storage service.
     * </p>
     *
     * @return the {@link FileStorageService} object
     */
    FileStorageService getFileStorageService();

}
