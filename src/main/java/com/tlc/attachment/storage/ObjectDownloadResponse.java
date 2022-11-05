package com.tlc.attachment.storage;

import java.io.InputStream;

/**
 * <p>
 *     {@code ObjectDownloadResponse} contains the object download response information.
 * </p>
 *
 * @param stream represents the {@link java.io.InputStream} that contains the object data
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public record ObjectDownloadResponse(InputStream stream) {
}
