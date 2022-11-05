package com.tlc.attachment.service;

import com.tlc.attachment.storage.ObjectDownloadResponse;
import com.tlc.attachment.storage.ObjectUploadResponse;

import java.io.InputStream;

/**
 * <p>
 *     Provides the object storage service.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public interface ObjectStorageService {

    /**
     * <p>
     *     Uploads the object
     * </p>
     *
     * @param inputStream represents the {@link java.io.InputStream} that contains the object data
     * @param objectName  represents the name of the object
     * @return the object upload response information
     */
    ObjectUploadResponse uploadObject(final InputStream inputStream, final String objectName);

    /**
     * <p>
     *      Downloads the object given in the bucket.
     * </p>
     *
     * @param versionId  represents the version id of the object
     * @param objectName represents the name of the object
     * @return the object download response information
     */
    ObjectDownloadResponse downloadObject(final String versionId, final String objectName);

}
