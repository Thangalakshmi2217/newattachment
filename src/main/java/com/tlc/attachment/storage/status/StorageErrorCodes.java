package com.tlc.attachment.storage.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * <p>
 *     An enum containing {@link StorageErrorCodes} which stores the status error codes as a separate group.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public enum StorageErrorCodes implements ErrorCodeProvider {

    STORAGE_SERVICE_INITIALIZATION_FAILED(0x21),
    OBJECT_UPLOAD_FAILED(0x22),
    OBJECT_DOWNLOAD_FAILED(0x23),
    BUCKET_CREATION_FAILED(0x24),
    BUCKET_VERSIONING_FAILED(0x25),
    BUCKET_NOT_FOUND(0x26),

    FILE_NOT_FOUND(0x27),
    UNSUPPORTED_FILE_EXTENSION(0x28),
    FILE_DIRECTORY_NOT_FOUND(0x29),
    ;

    private final int code;

    StorageErrorCodes(int localCode) {
        this.code = StorageErrorCodeGroup.GROUP.getConvertedCode(localCode);
    }

    @Override
    public int getCode()
    {
        return code;
    }

    /**
     * <p>
     *
     * </p>
     */
    private static class StorageErrorCodeGroup implements ErrorCodeGroup {
        private static final ErrorCodeGroup GROUP = new StorageErrorCodeGroup();

        @Override
        public int getPrefix()
        {
            return 0x35_0_0001;
        }
    }

}
