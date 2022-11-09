package com.tlc.attachment.service;

import com.tlc.attachment.storage.ObjectDownloadResponse;
import com.tlc.attachment.storage.ObjectUploadResponse;

import java.io.InputStream;

/**
 * <p>
 *     Provides object storage services.
 * </p>
 *
 * @author ThalaimalaiPandiyan T
 * @version 1.0
 */
public interface ObjectStorageService {

    /**
     * <p>
     *     Uploads the object in the path.
     * </p>
     *
     * @param inputStream represents the {@link java.io.InputStream} containing the object data
     * @param objectName  represents the name of the object
     * @return the {@link ObjectUploadResponse} object
     */
    ObjectUploadResponse uploadObject(final InputStream inputStream, final String objectName);

    /**
     * <p>
     *      Downloads the object using version id and the object name.
     * </p>
     *
     * @param versionId  represents the specific version of the object
     * @param objectName represents the name of the object
     * @return the {@link ObjectDownloadResponse} object
     */
    ObjectDownloadResponse downloadObject(final String versionId, final String objectName);

}
