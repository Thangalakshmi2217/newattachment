package com.tlc.attachment.service;

import com.tlc.attachment.storage.ObjectDownloadResponse;
import com.tlc.attachment.storage.ObjectUploadResponse;

import java.io.InputStream;

/**
 * <p>
 *     Provides object storage services.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public interface ObjectStorageService {

    /**
     * <p>
     *     Uploads the object using the object name.
     * </p>
     *
     * @param inputStream represents the {@link java.io.InputStream} which contains the object data
     * @param objectName  represents the name of the object
     * @return the {@link ObjectUploadResponse} object
     */
    ObjectUploadResponse uploadObject(final InputStream inputStream, final String objectName);

    /**
     * <p>
     *      Downloads the object using version id and the object name.
     * </p>
     *
     * @param versionId  represents the version id of the object
     * @param objectName represents the name of the object
     * @return the {@link ObjectDownloadResponse} object
     */
    ObjectDownloadResponse downloadObject(final String versionId, final String objectName);

}
