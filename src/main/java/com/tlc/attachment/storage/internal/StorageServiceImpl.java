package com.tlc.attachment.storage.internal;

import com.tlc.attachment.service.FileStorageService;
import com.tlc.attachment.service.ObjectStorageService;
import com.tlc.attachment.storage.internal.config.ObjectStorageConfig;
import com.tlc.commons.code.ErrorCode;
import com.tlc.commons.service.Service;
import com.tlc.attachment.storage.status.StorageErrorCodes;
import com.tlc.attachment.service.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p>
 *     Enables and disables the storage service and retrieves the file and object storage service.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public class StorageServiceImpl implements StorageService, Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);
    private ObjectStorageService objectStorageService;
    private FileStorageService fileStorageService;

    /**
     * <p>
     *     Storage service gets initialized.
     * </p>
     *
     * @param input represents the string as both key and value
     */
    @Override
    public void start(final Map<String, String> input) {
        try {
            this.objectStorageService = new ObjectStorageServiceImpl(new ObjectStorageConfig(input));
            this.fileStorageService = FileStorageServiceImpl.getInstance();
        } catch (Exception exception) {
            throw ErrorCode.get(StorageErrorCodes.STORAGE_SERVICE_INITIALIZATION_FAILED, exception.getMessage());
        }
        LOGGER.info("Storage Service Activated");
    }

    /**
     * <p>
     *     Storage service gets deactivated.
     * </p>
     */
    @Override
    public void stop() {
        LOGGER.info("Storage Service Deactivated");
    }

    /**
     * {@inheritDoc}
     *
     * @return the {@link ObjectStorageService}
     */
    @Override
    public ObjectStorageService getObjectStorageService() {
        return objectStorageService;
    }

    /**
     * {@inheritDoc}
     *
     * @return the {@link FileStorageService}
     */
    @Override
    public FileStorageService getFileStorageService() {
        return fileStorageService;
    }

}
