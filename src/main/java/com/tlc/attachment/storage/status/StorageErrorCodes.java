package com.tlc.attachment.storage.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * <p>
 *     An enum which holds {@link StorageErrorCodes} that stores the status error codes.
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

    /**
     * <p>
     *     Gets the code.
     * </p>
     *
     * @return the code
     */
    @Override
    public int getCode()
    {
        return code;
    }

    /**
     * <p>
     *     Storage error codes are stored as a separate group.
     * </p>
     */
    private static class StorageErrorCodeGroup implements ErrorCodeGroup {
        private static final ErrorCodeGroup GROUP = new StorageErrorCodeGroup();

        /**
         * <p>
         *     Gets the prefix.
         * </p>
         *
         * @return the error code
         */
        @Override
        public int getPrefix()
        {
            return 0x35_0_0001;
        }
    }

}
